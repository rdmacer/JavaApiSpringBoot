package com.api.service;

import com.api.domain.dao.ExchangeDao;
import com.api.domain.entitys.Exchange;
import com.api.ui.ExchangeRequest;
import com.api.ui.ExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public class ExchangeRateService {

  @Autowired
  ExchangeDao exchangeDao;

  public Single<ExchangeResponse> applyExchangeRate(ExchangeRequest exchangeRequest) {
    try {
      ExchangeResponse exchangeResponse = new ExchangeResponse();
      Double rateSource = exchangeDao.findRateByCurrencies(exchangeRequest.sourceCurrency,
              exchangeRequest.targetCurrency);
      Double rateTarget;
      if (rateSource == null) {
        rateTarget = exchangeDao.findRateByCurrencies(exchangeRequest.targetCurrency, exchangeRequest.sourceCurrency);
        if (rateTarget == null) {
          throw new Exception("Not match currencies for exchange");
        }
        exchangeResponse.rate = rateTarget;
        exchangeResponse.amountChanged = exchangeRequest.amount * rateTarget;
      } else {
        exchangeResponse.rate = rateSource;
        exchangeResponse.amountChanged = exchangeRequest.amount / rateSource;
      }
      exchangeResponse.amountChanged = Math.round(exchangeResponse.amountChanged * 100.0) / 100.0;
      exchangeResponse.amount = exchangeRequest.amount;
      exchangeResponse.sourceCurrency = exchangeRequest.sourceCurrency;
      exchangeResponse.targetCurrency = exchangeRequest.targetCurrency;
      return Single.just(exchangeResponse);
    } catch (Throwable error) {
      return Single.error(error);
    }
  }

  public Single<String> updateExchangeRate(ExchangeRequest exchangeRequest) {
    try {
      Exchange exchange = exchangeDao.findExchangeByCurrencies(exchangeRequest.sourceCurrency,
              exchangeRequest.targetCurrency);
      if (exchange == null) {
        exchange = exchangeDao.findExchangeByCurrencies(exchangeRequest.targetCurrency, exchangeRequest.sourceCurrency);
      }
      if (exchange == null) {
        throw new Exception("Not match currencies for update exchange");
      }
      exchange.setRate(exchangeRequest.newRate);
      exchangeDao.save(exchange);
      return Single.just("Exchange rate successful updated");
    } catch (Throwable error) {
      return Single.error(error);
    }
  }
}
