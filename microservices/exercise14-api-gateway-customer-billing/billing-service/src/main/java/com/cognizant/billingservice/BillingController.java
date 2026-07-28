package com.cognizant.billingservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingController {

    @GetMapping("/billing/{id}")
    public String getBilling(@PathVariable String id) {
        return "Billing details for id: " + id;
    }

}
