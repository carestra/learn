package se.carestra.learn.spring.jdbc.postgresqldriver.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import se.carestra.learn.spring.jdbc.postgresqldriver.Customer;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    assertThat(customers.size(), is(2));

    customers.forEach(System.out::println);
  }
}
