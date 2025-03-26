package se.carestra.learn.spring.jdbc.postgresqldriver.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import se.carestra.learn.spring.jdbc.postgresqldriver.Customer;

import java.util.List;

public class CustomerJbdcTemplateTransactionalAnnotatedRepository implements CustomerRepository {
  private final JdbcTemplate jdbcTemplate;

  public CustomerJbdcTemplateTransactionalAnnotatedRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.jdbcTemplate.afterPropertiesSet();
  }

  @Transactional
  @Override
  public List<Customer> findAll() {
    return jdbcTemplate.query(
        "select * from customer",
        (rs, rowNum) ->
            new Customer(rs.getInt("id"), rs.getString("name"))
    );
  }
}
