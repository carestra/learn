package se.carestra.learn.spring.jdbc.postgresqldriver.repository;

import se.carestra.learn.spring.jdbc.postgresqldriver.Customer;

import java.util.List;

public interface CustomerRepository {
  List<Customer> findAll();
}
