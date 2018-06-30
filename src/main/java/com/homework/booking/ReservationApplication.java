package com.homework.booking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class ReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservationApplication.class, args);
    }
}
