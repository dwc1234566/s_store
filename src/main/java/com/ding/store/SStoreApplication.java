package com.ding.store;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ding.store.mapper")
public class SStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SStoreApplication.class, args);
    }

}
