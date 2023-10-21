package com.buba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan("com.buba.mapper")
public class BookGuanliApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookGuanliApplication.class, args);
    }

}
