package com.cognizant.exampleservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/example/hello")
    public String hello() {
        return "Hello from example-service instance running on port: " + port;
    }

}
