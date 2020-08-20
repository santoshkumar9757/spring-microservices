package org.santosh.microservices.currencyexchangeservice.repository;

import org.santosh.microservices.currencyexchangeservice.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateJpaRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findByFromAndTo(String from, String to);
}
