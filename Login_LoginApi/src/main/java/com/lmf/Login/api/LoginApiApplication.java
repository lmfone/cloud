package com.lmf.Login.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * projectName: Login
 * author: 李梦飞
 * time: 2020/11/5 20:58
 * description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@RibbonClients
public class LoginApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginApiApplication.class,args);
    }
}
