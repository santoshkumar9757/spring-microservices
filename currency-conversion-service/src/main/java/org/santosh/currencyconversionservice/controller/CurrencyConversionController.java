package org.santosh.currencyconversionservice.controller;

import org.santosh.currencyconversionservice.CurrencyExchangeServiceProxy;
import org.santosh.currencyconversionservice.bean.CurrencyConversionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {
    private Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    private CurrencyExchangeServiceProxy serviceProxy;

    @GetMapping("currency-conversion/{from}/to/{to}/{quantity}")
    public CurrencyConversionBean getCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

            Map<String, String> urlVariables = new HashMap<>();
            urlVariables.put("from", from);
            urlVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().
                getForEntity("http://localhost:8001/currency-exchange/{from}/to/{to}", CurrencyConversionBean.class, urlVariables);

        CurrencyConversionBean bean = responseEntity.getBody();
        return new CurrencyConversionBean(bean.getId(), bean.getFrom(), bean.getTo(), bean.getExchangeRate(), quantity.multiply(bean.getExchangeRate()), quantity, bean.getPort());

    }

    @GetMapping("currency-conversion-feign/{from}/to/{to}/{quantity}")
    public CurrencyConversionBean getCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        CurrencyConversionBean bean = serviceProxy.retrieveExchangeRate(from, to);
        logger.info("Currency Conversion Bean:-> {}", bean);
        return new CurrencyConversionBean(bean.getId(), bean.getFrom(), bean.getTo(), bean.getExchangeRate(), quantity.multiply(bean.getExchangeRate()), quantity, bean.getPort());

    }
}
