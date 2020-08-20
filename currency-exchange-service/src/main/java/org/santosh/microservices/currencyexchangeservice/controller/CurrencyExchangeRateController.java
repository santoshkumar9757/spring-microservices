package org.santosh.microservices.currencyexchangeservice.controller;

import org.santosh.microservices.currencyexchangeservice.model.ExchangeRate;
import org.santosh.microservices.currencyexchangeservice.repository.ExchangeRateJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeRateController {
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeRateController.class);
    @Autowired
    private Environment environment;

    @Autowired
    ExchangeRateJpaRepository exchangeRateJpaRepository;

    @GetMapping("/currency-exchange/{from}/to/{to}")
    public ExchangeRate retrieveExchangeRate(@PathVariable String from, @PathVariable String to) {
        ExchangeRate exchangeRate = exchangeRateJpaRepository.findByFromAndTo(from, to);
        exchangeRate.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        logger.info("Exchange Rate:-> {}" , exchangeRate);

       return exchangeRate;
    }
}
