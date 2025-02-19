package com.adesso.mgpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class MgptApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MgptApplication.class, args);
        System.out.println("Starting application with beans:");
        for (String bean : ctx.getBeanDefinitionNames())
            System.out.println("  " + bean);
    }

}
