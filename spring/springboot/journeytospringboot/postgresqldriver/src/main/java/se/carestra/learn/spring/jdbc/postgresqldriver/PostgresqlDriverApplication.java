package se.carestra.learn.spring.jdbc.postgresqldriver;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePropertySource;
import se.carestra.learn.spring.jdbc.postgresqldriver.repository.CustomerRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class PostgresqlDriverApplication {

  public static void main(String[] args) throws IOException {
    //ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

    // below add the properties to the context before registering the AppConfig class.
//    var context = new AnnotationConfigApplicationContext();
//    var env = context.getEnvironment();
//    initEnv(env);
//    context.register(AppConfiguration.class);
//    context.refresh();
//    context.start();

    var context = SpringApplication.run(AppConfiguration.class, args);

    CustomerRepository repository = context.getBean(CustomerRepository.class);
    repository.findAll().forEach(System.out::println);
  }

  private static void initEnv(ConfigurableEnvironment env) throws IOException {
    var propertiesFile = (Resource) new ClassPathResource("/application.properties");
    var properties = new Properties();
    try {
      properties.load(propertiesFile.getInputStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    var map = new HashMap<String, Object>();
    properties.forEach((k, v) -> map.put(k.toString(), v));

    //env.getPropertySources().addLast(new MapPropertySource("bootiful", map));

    // the above can be replace with
    env.getPropertySources()
        .addLast(new ResourcePropertySource(new ClassPathResource("/application.properties")));
  }

}
