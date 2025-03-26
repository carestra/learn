package se.carestra.learn.spring.jdbc.postgresqldriver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import se.carestra.learn.spring.jdbc.postgresqldriver.repository.CustomerJbdcTemplateRepository;
import se.carestra.learn.spring.jdbc.postgresqldriver.repository.CustomerJbdcTemplateTransactionalAnnotatedRepository;
import se.carestra.learn.spring.jdbc.postgresqldriver.repository.CustomerRepository;
import se.carestra.learn.spring.jdbc.postgresqldriver.repository.TransactionalProxy;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class AppConfiguration {

  @Bean
  DataSource dataSource(Environment environment) {
  //DataSource dataSource() {
    var url = environment.getProperty("spring.datasource.url");
    var username = environment.getProperty("spring.datasource.username");
    var password = environment.getProperty("spring.datasource.password");

    HikariConfig configuration = new HikariConfig();
    configuration.setDriverClassName("org.postgresql.Driver");
    configuration.setJdbcUrl(url);
    configuration.setUsername(username);
    configuration.setPassword(password);

    return new HikariDataSource(configuration);
  }

  @Bean
  PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
    return new TransactionTemplate(transactionManager);
  }

  @Bean
  JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  CustomerRepository customerRepository(JdbcTemplate jdbcTemplate) {
  // CustomerRepository customerRepository(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
//    return (CustomerRepository) TransactionalProxy
//        .build(
//            transactionTemplate,
//            new CustomerJbdcTemplateTransactionalAnnotatedRepository(jdbcTemplate)
//        );

    // remove annotation @EnableTransactionManagement and uncomment above to work
    return new CustomerJbdcTemplateRepository(jdbcTemplate);
  }
}
