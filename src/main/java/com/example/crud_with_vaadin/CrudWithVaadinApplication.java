package com.example.crud_with_vaadin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudWithVaadinApplication {

    private static final Logger log = LoggerFactory.getLogger(CrudWithVaadinApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CrudWithVaadinApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(CustomerRepository repository) {
        return (args) -> {
            repository.save(new Customer("Jack", "Bauer C12"));
            repository.save(new Customer("Chloe", "O'Brian C13"));
            repository.save(new Customer("Kim", "Bauer C14"));
            repository.save(new Customer("David", "Palmer C15"));
            repository.save(new Customer("Michelle", "Dessler C16"));

            log.info("Customers found with findAll():");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
        };
    }
}