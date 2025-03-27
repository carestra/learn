package se.carestra.learn.spring.jdbc.postgresqldriver.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.carestra.learn.spring.jdbc.postgresqldriver.Customer;

import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomerRepository2Tests {

  private CustomerRepository repo;

  CustomerRepository2Tests(@Autowired CustomerRepository repo) {
    this.repo = repo;
  }

  @Test
  void findAll() {
    List<Customer> customers = repo.findAll();
    assertNotNull(customers);
    assertThat(customers.isEmpty(), is(false));
    assertThat(customers.size(), is(5));

    customers.forEach(System.out::println);
  }

  // Using 'spring.main.allow-bean-definition-overriding=true' to override same bean name as in 'AppConfiguration.java'.
  @TestConfiguration
  static class Configuration {
    @Bean
    public DataSource dataSource() {
      return new EmbeddedDatabaseBuilder()
          .generateUniqueName(true)
          .setType(EmbeddedDatabaseType.H2)
          .addScripts("schema.sql", "data.sql")
          .build();
    }

    @Bean
    public CustomerRepository customerRepository() {
      return new CustomerDatasourceRepository(dataSource());
    }
  }
}
