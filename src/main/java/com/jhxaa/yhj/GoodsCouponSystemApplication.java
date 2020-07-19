package com.jhxaa.yhj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

//@ComponentScan(basePackages = "com.jhxaa.yhj.*")
@MapperScan("com.jhxaa.yhj.mapper") //扫描dao层
@SpringBootApplication
@EnableAsync//开启异步
public class GoodsCouponSystemApplication {


    public static void main(String[] args) {

        SpringApplication.run(GoodsCouponSystemApplication.class, args);
    }

}
