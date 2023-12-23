package com.project.PropertyVersatile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.project.PropertyVersatile")
@ComponentScan(basePackages = "com.project.PropertyVersatile") // Specify your base package
@EntityScan(basePackages = "com.project.PropertyVersatile.entity") // Specify the package containing your entities
public class PropertyVersatileApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertyVersatileApplication.class, args);
    }

}
