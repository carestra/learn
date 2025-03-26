package se.carestra.learn.spring.jdbc.postgresqldriver.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import se.carestra.learn.spring.jdbc.postgresqldriver.Customer;

import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerRepositoryTests {
  private DataSource dataSource;
  private JdbcTemplate jdbcTemplate;
  private PlatformTransactionManager transactionManager;
  private TransactionTemplate transactionTemplate;

  @BeforeEach
  public void setUp() {

    HikariConfig configuration = new HikariConfig();
    configuration.setDriverClassName("org.postgresql.Driver");
    configuration.setJdbcUrl("jdbc:postgresql://localhost:5432/");
    configuration.setUsername("postgres");
    configuration.setPassword("example");

    dataSource = new HikariDataSource(configuration);
    jdbcTemplate = new JdbcTemplate(dataSource);
    transactionManager = new DataSourceTransactionManager(dataSource);
    transactionTemplate = new TransactionTemplate(transactionManager);
  }

  @Test
  public void findAll() {
    //CustomerRepository repo = new CustomerDriverManagerRepository();
    //CustomerRepository repo = new CustomerDatasourceRepository(dataSource);
    //CustomerRepository repo = new CustomerJbdcTemplateRepository(jdbcTemplate);
    //CustomerRepository repo = new CustomerTransactionManagerRepository(jdbcTemplate, transactionManager);
    //CustomerRepository repo = new CustomerTransactionTemplateRepository(jdbcTemplate, transactionTemplate);
    //CustomerRepository repo = (CustomerRepository) TransactionalProxy.build(transactionTemplate, new CustomerJbdcTemplateRepository(jdbcTemplate));
    CustomerRepository repo = (CustomerRepository) TransactionalProxy.build(transactionTemplate, new CustomerJbdcTemplateTransactionalAnnotatedRepository(jdbcTemplate));
    List<Customer> customers = repo.findAll();
    assertNotNull(customers);
    assertThat(customers.isEmpty(), is(false));
    assertThat(customers.size(), is(2));

    customers.forEach(System.out::println);
  }
}
