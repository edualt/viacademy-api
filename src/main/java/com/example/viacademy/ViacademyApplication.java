package com.example.viacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ViacademyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViacademyApplication.class, args);
    }

}
