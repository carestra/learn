package se.carestra.learn.spring.jdbc.postgresqldriver.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import se.carestra.learn.spring.jdbc.postgresqldriver.Customer;

import java.util.List;

public class CustomerTransactionTemplateRepository implements CustomerRepository {
  private final JdbcTemplate jdbcTemplate;
  private final TransactionTemplate transactionTemplate;

  public CustomerTransactionTemplateRepository(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.jdbcTemplate.afterPropertiesSet();
    this.transactionTemplate = transactionTemplate;
  }

  @Override
  public List<Customer> findAll() {
    return transactionTemplate.execute(
        status ->
            jdbcTemplate.query(
                "select * from customer",
                (rs, rowNum) ->
                    new Customer(rs.getInt("id"), rs.getString("name"))
            )
    );
  }
}
