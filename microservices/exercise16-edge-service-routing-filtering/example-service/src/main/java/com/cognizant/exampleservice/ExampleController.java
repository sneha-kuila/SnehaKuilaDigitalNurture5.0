package com.cognizant.exampleservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/example/hello")
    public String hello() {
        return "Hello from example-service! You reached me through the edge service.";
    }

}
