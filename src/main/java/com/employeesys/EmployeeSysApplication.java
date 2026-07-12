package com.employeesys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeSysApplication {
    String name;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeSysApplication.class, args);
    }
}