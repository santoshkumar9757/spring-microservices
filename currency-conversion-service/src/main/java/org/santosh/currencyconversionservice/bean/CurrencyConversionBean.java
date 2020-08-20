package org.santosh.currencyconversionservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionBean {

    private Long id;
    private String from;
    private String to;
    private BigDecimal exchangeRate;
    private BigDecimal totalAmount;
    private BigDecimal quantity;
    private int port;

}
