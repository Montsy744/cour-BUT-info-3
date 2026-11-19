package fr.but3.server;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ServerApplication implements ApplicationRunner {
  public static void main(String[] args) {
    @SuppressWarnings(value = "unused")
    ApplicationContext context = SpringApplication.run(ServerApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("hello");
  }
}
