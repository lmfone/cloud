package com.lmf.login.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan(basePackages = "com.lmf.Login.provider.dao")
@EnableDiscoveryClient
@EnableScheduling //启用定时任务
public class LoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class,args);
    }
}