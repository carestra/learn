package se.carestra.learn.spring.jdbc.postgresqldriver.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import se.carestra.learn.spring.jdbc.postgresqldriver.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerTransactionManagerRepository implements CustomerRepository {
  private final JdbcTemplate jdbcTemplate;
  private final PlatformTransactionManager transactionManager;

  public CustomerTransactionManagerRepository(JdbcTemplate jdbcTemplate, PlatformTransactionManager transactionManager) {
    this.jdbcTemplate = jdbcTemplate;
    this.jdbcTemplate.afterPropertiesSet();
    this.transactionManager = transactionManager;
  }

  @Override
  public List<Customer> findAll() {
    List<Customer> result = new ArrayList<Customer>();
    TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
    try {
      result = jdbcTemplate.query(
          "select * from customer",
          (rs, rowNum) ->
              new Customer(rs.getInt("id"), rs.getString("name"))
      );
      transactionManager.commit(transactionStatus);
    } catch (Throwable e) {
      System.err.println(e.getMessage());
      transactionManager.rollback(transactionStatus);
    }

    return result;
  }
}
