package com.example.kubernetesdemo.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello from Spring Boot + Kubernetes!";
    }

    @GetMapping("/api/health")
    public String health() {
        return "Application is UP";
    }
}
