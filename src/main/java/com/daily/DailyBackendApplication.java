package com.daily;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.daily.dao")
@SpringBootApplication
@EnableScheduling
public class DailyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyBackendApplication.class, args);
    }

}
