package com.api.domain.dao;

import com.api.domain.entitys.Exchange;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeDao extends CrudRepository<Exchange, Long> {
  Double findRateByCurrencies(String sourceCurrency, String targetCurrency);

  Exchange findExchangeByCurrencies(String sourceCurrency, String targetCurrency);
}