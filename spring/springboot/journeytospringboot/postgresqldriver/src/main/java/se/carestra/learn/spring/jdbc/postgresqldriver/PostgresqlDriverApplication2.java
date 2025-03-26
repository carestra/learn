package se.carestra.learn.spring.jdbc.postgresqldriver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.carestra.learn.spring.jdbc.postgresqldriver.repository.CustomerRepository;

import java.io.IOException;

@SpringBootApplication
public class PostgresqlDriverApplication2 {

  public static void main(String[] args) {
    var context = SpringApplication.run(PostgresqlDriverApplication2.class, args);

    CustomerRepository repository = context.getBean(CustomerRepository.class);
    repository.findAll().forEach(System.out::println);
  }

}
