package com.example.jobms.job;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced   //Load balance is neede when you want to balance communication between servers of eureka
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
