package com.example.jymapplication;

import io.prometheus.client.Counter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GymApplication {
    static final Counter requestsTotal = Counter.build()
            .name("requests_total")
            .help("Total number of requests.")
            .register();


    public static void main(String[] args) {
        SpringApplication.run(GymApplication.class, args);

    }
}
