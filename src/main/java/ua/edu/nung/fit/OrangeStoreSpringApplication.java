package ua.edu.nung.fit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Ця анотація каже Spring, що це головний клас
public class OrangeStoreSpringApplication {
    public static void main(String[] args) {
        // Запуск всього механізму Spring Boot
        SpringApplication.run(OrangeStoreSpringApplication.class, args);
    }
}