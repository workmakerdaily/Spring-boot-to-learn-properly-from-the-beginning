package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.David;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SburRestDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SburRestDemoApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix = "david")
    David createDavid() {
        return new David();
    }
}
