package com.yiblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yiblog.mapper")
public class YiblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiblogApplication.class, args);
    }

}
