package de.htw.imi.springdatajpa.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "de.htw.imi.springdatajpa")
@EnableJpaRepositories(basePackages = "de.htw.imi.springdatajpa.repos")
@ComponentScan(basePackages = { "de.htw.imi.springdatajpa.*" })
@EntityScan("de.htw.imi.springdatajpa.entities")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
