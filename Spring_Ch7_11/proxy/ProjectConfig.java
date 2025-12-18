package com.example.proxy;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableFeignClients(
basePackages = "com.example.proxy")
public class ProjectConfig {
    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
            .build();
    }
}
