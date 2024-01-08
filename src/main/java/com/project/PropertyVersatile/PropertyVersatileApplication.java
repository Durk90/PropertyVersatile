package com.project.PropertyVersatile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.project.PropertyVersatile.entity")
public class PropertyVersatileApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertyVersatileApplication.class, args);
    }
}
