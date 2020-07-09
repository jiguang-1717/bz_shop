package com.bjsxt.cart;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 购物车服务
 */
@SpringBootApplication
@EnableDiscoveryClient
//开启feign
@EnableFeignClients
public class FrontendCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendCartApplication.class,args);
    }
}
