package org.santosh.microservices.currencyexchangeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EXCHANGERATE")
public class ExchangeRate {
    @Id
    private long id;

    @Column(name = "from_currency")
    private String from;
    @Column(name = "to_currency")
    private String to;
    @Column(name = "rate")
    private BigDecimal exchangeRate;
    private int port;

    public ExchangeRate(int id, String from, String to, BigDecimal exchangeRate) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.exchangeRate = exchangeRate;
    }
}
