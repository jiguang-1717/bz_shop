package com.bjsxt.item;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//将服务注册到eureka中
@EnableDiscoveryClient
@MapperScan("com.bjsxt.mapper")
public class CommonItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonItemApplication.class,args);
    }
}
