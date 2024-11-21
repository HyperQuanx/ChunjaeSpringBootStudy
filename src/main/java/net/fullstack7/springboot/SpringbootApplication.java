package net.fullstack7.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

// 스프링 프레임워크
@EnableJpaAuditing
public class SpringbootApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootApplication.class, args);
  }

}
