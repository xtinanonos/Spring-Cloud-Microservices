package com.ccsw.tutorial_loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TutorialLoanApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialLoanApplication.class, args);
    }

}
