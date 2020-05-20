package com.hnit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//mybaits包扫描
@MapperScan("com.hnit.dao")
public class XBDC {
    public static void main(String[] args) {
        SpringApplication.run(XBDC.class,args);
    }
}
