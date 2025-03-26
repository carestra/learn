package se.carestra.learn.spring.jdbc.postgresqldriver.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import se.carestra.learn.spring.jdbc.postgresqldriver.Customer;

import java.util.List;

public class CustomerJbdcTemplateRepository implements CustomerRepository {
  private final JdbcTemplate jdbcTemplate;

  public CustomerJbdcTemplateRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.jdbcTemplate.afterPropertiesSet();
  }

  @Override
  public List<Customer> findAll() {
    return jdbcTemplate.query(
        "select * from customer",
        (rs, rowNum) ->
            new Customer(rs.getInt("id"), rs.getString("name"))
    );
  }
}
