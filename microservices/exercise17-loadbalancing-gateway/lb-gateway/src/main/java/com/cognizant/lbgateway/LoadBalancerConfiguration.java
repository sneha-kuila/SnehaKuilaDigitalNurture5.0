package com.cognizant.lbgateway;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * Custom load balancer configuration - uses a Random strategy instead of the
 * default Round Robin, to demonstrate that load balancing behaviour is
 * pluggable in Spring Cloud LoadBalancer.
 *
 * NOTE: This class is intentionally NOT annotated with @Configuration.
 * It is wired in via @LoadBalancerClient(name = "example-service", configuration = LoadBalancerConfiguration.class)
 * on the main application class, which scopes this custom strategy to only
 * the "example-service" client instead of overriding the default globally.
 */
public class LoadBalancerConfiguration {

    @Bean
    public ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(
            Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(
                loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }

}
