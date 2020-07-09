package com.bjsxt.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//开启feign
@EnableFeignClients
@MapperScan("com.bjsxt.mapper")
public class FrontendSsoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendSsoApplication.class,args);
    }
}
