package se.carestra.learn.spring.endtoend.dogadopterconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class DogAdopterConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogAdopterConfigApplication.class, args);
	}
}
