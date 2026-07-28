package com.cognizant.customerservice;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Cacheable("customers")
    @GetMapping("/customers/{id}")
    public String getCustomer(@PathVariable String id) {
        return "Customer details for id: " + id;
    }

}
