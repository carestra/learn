package se.carestra.learn.spring.jdbc.postgresqldriver.repository;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import se.carestra.learn.spring.jdbc.postgresqldriver.Customer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class CustomerDriverManagerRepository implements CustomerRepository {
  private final DataSource dataSource;

  public CustomerDriverManagerRepository() {
    this.dataSource = new DriverManagerDataSource(
        "jdbc:postgresql://localhost:5432/postgres",
        "postgres",
        "example");
  }

  public List<Customer> findAll() {
    var result = new ArrayList<Customer>();
    try {
      try (var connection = dataSource.getConnection()) {
        try (var statement = connection.prepareStatement("select * from customer")) {
          var resultSet = statement.executeQuery();

          while (resultSet.next()) {
            result.add(new Customer(resultSet.getInt("id"), resultSet.getString("name")));
          }
        }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return result;
  }
}
