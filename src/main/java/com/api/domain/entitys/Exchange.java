package com.api.domain.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = Exchange.FIND_RATE_BY_CURRENCIES, query =
        "SELECT e.rate FROM Exchange e WHERE e.sourceCurrency = :sourceCurrency AND e.targetCurrency = "
                + ":targetCurrency"),
        @NamedQuery(name = Exchange.FIND_EXCHANGE_BY_CURRENCIES, query =
        "SELECT e FROM Exchange e WHERE e.sourceCurrency = :sourceCurrency AND e.targetCurrency = "
                + ":targetCurrency") })
@Table(name = "Exchange")
public class Exchange implements Serializable {

  public static final String FIND_RATE_BY_CURRENCIES = "Exchange.findRateByCurrencies";

  public static final String FIND_EXCHANGE_BY_CURRENCIES = "Exchange.findExchangeByCurrencies";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String sourceCurrency;

  private String targetCurrency;

  private Double rate;

  public Integer getId() {
    return id;
  }

  public String getSourceCurrency() {
    return sourceCurrency;
  }

  public void setSourceCurrency(String sourceCurrency) {
    this.sourceCurrency = sourceCurrency;
  }

  public String getTargetCurrency() {
    return targetCurrency;
  }

  public void setTargetCurrency(String targetCurrency) {
    this.targetCurrency = targetCurrency;
  }

  public Double getRate() {
    return rate;
  }

  public void setRate(Double rate) {
    this.rate = rate;
  }
}
