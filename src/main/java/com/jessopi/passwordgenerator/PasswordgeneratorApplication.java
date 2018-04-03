package com.jessopi.passwordgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    Web application that generates random passwords based on selected parameters chosen by the user.
    Programmed by Ian Jessop
*/
@SpringBootApplication
public class PasswordgeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasswordgeneratorApplication.class, args);
    }
}
