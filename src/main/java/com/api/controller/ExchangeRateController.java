package com.api.controller;

import com.api.service.ExchangeRateService;
import com.api.ui.ExchangeRequest;
import com.api.ui.ExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rx.Single;
import rx.schedulers.Schedulers;

@RestController
@RequestMapping("/api")
public class ExchangeRateController {

  final ExchangeRateService exchangeRateService;

  @Autowired
  public ExchangeRateController(ExchangeRateService exchangeRateService) {
    this.exchangeRateService = exchangeRateService;
  }

  @PostMapping("/exchangeRate")
  public Single<ExchangeResponse> handleGetExchangeRate(@RequestBody ExchangeRequest exchangeRequest) {
        return exchangeRateService.applyExchangeRate(exchangeRequest).doOnError(error -> System.out.println(
                "Error on handleGetExchangeRate:"+error))
                      .subscribeOn(Schedulers.io());

  }

  @PutMapping("/updateExchangeRate")
  public Single<String> handleUpdateExchangeRate(@RequestBody ExchangeRequest exchangeRequest) {
    return exchangeRateService.updateExchangeRate(exchangeRequest).doOnError(error -> System.out.println(
                                      "Error on handleUpdateExchangeRate:"+error))
                              .subscribeOn(Schedulers.io());

  }

}
