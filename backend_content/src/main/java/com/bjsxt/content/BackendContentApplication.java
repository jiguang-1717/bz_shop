package com.bjsxt.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//开启feign
@EnableFeignClients
public class BackendContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendContentApplication.class,args);
    }
}
