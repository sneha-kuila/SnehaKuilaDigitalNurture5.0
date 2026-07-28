package com.cognizant.lbgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@LoadBalancerClient(name = "example-service", configuration = LoadBalancerConfiguration.class)
@SpringBootApplication
public class LbGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LbGatewayApplication.class, args);
    }

}
