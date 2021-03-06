package com.lmf.Login.api.config;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * projectName: Login
 * author: 李梦飞
 * time: 2020/11/5 20:58
 * description:
 */
@Configuration
public class RibbonConfig {
    @Bean
    @LoadBalanced//启动负载均衡
    public RestTemplate createRT(){
        return new RestTemplate();
    }
    //负载均衡的分发策略
    @Bean
    public IRule createRule(){
        //最小并发分配
        return new BestAvailableRule();
    }
}
