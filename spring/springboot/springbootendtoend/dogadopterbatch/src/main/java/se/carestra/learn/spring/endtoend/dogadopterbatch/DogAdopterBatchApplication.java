package se.carestra.learn.spring.endtoend.dogadopterbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@SpringBootApplication
public class DogAdopterBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogAdopterBatchApplication.class, args);
	}

	@Bean
	FlatFileItemReader<Dog> dogFlatFileItemReader(@Value("classpath:/dogs.csv") Resource dogCsv) {
		return new FlatFileItemReaderBuilder<Dog>()
				.linesToSkip(1)
				.resource(dogCsv)
				.name("dogsCsvToDB")
				.fieldSetMapper(fieldSet ->
						new Dog(
								fieldSet.readInt("id"),
								fieldSet.readString("name"),
								fieldSet.readString("owner"),
								fieldSet.readString("description")
								)
				)
				.delimited()
				.names("id,name,description,dob,owner,gender,image".split(","))
				.build();
	}
	@Bean
	JdbcBatchItemWriter<Dog> dogJdbcBatchItemWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Dog>()
				.dataSource(dataSource)
				.assertUpdates(true)
				.sql("insert into dog(id, name, owner, description) values(?,?,?,?)")
				.itemPreparedStatementSetter( (item, ps) -> {
					ps.setInt(1, item.id());
					ps.setString(2, item.name());
					ps.setString(3, item.owner());
					ps.setString(4, item.description());
				})
				.build();
	}

	@Bean
	Step csvToDBStep(JobRepository jobRepository,
									 PlatformTransactionManager transactionManager,
									 FlatFileItemReader<Dog> dogFlatFileItemReader,
									 JdbcBatchItemWriter<Dog> dogJdbcBatchItemWriter) {
		return new StepBuilder("csvToDBStep", jobRepository)
				.<Dog,Dog>chunk(10, transactionManager)
				.reader(dogFlatFileItemReader)
				.writer(dogJdbcBatchItemWriter)
				.build();
	}
	@Bean
	Job csvToDBJob(JobRepository jobRepository, Step step) {
		return new JobBuilder("csvToDB", jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
