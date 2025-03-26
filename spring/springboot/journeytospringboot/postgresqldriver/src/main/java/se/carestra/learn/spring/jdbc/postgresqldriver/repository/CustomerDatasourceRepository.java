package se.carestra.learn.spring.jdbc.postgresqldriver.repository;

import se.carestra.learn.spring.jdbc.postgresqldriver.Customer;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class CustomerDatasourceRepository implements CustomerRepository {
  private final DataSource dataSource;

  public CustomerDatasourceRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
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
