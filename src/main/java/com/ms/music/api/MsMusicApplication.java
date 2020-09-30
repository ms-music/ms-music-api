package com.ms.music.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ms.music.api.*.mapper")
public class MsMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsMusicApplication.class, args);
    }

}
