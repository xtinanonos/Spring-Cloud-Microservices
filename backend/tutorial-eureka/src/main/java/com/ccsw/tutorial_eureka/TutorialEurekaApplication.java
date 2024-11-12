package com.ccsw.tutorial_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer        // habilita el servidor de Eureka

public class TutorialEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialEurekaApplication.class, args);
    }

}
