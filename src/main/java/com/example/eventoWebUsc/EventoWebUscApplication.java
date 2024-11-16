package com.example.eventoWebUsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EventoWebUscApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventoWebUscApplication.class, args);
    }
}
