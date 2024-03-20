package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan (basePackages = {"com.mapper"})
public class App0319 {
    public static void main( String[] args )
    {
        SpringApplication.run( App0319.class , args );

    }




}
