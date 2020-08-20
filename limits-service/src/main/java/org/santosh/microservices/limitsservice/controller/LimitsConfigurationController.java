package org.santosh.microservices.limitsservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.santosh.microservices.limitsservice.beans.LimitConfiguration;
import org.santosh.microservices.limitsservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfiguration() {
        return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }

    @GetMapping("/faulty-method")
    @HystrixCommand(fallbackMethod = "fallbackConfiguration")
    public LimitConfiguration faultyMethodConfiguration() {
        throw new RuntimeException();
    }

    @GetMapping("/hystrix-method")
    public LimitConfiguration fallbackConfiguration() {
        return new LimitConfiguration(5, 555);
    }
}
