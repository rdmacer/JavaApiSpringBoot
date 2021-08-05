package com.api.domain.dao.jpa;

import com.api.domain.dao.ExchangeDao;
import com.api.domain.entitys.Exchange;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaExchangeDao implements ExchangeDao {
  @PersistenceContext
  protected EntityManager entityManager;

  @Override
  public Double findRateByCurrencies(String sourceCurrency, String targetCurrency) {
    TypedQuery<Double> query = entityManager.createNamedQuery(Exchange.FIND_RATE_BY_CURRENCIES, Double.class);
    query.setParameter("sourceCurrency", sourceCurrency);
    query.setParameter("targetCurrency", targetCurrency);
    return query.getSingleResult();
  }

  @Override
  public Exchange findExchangeByCurrencies(String sourceCurrency, String targetCurrency) {
    TypedQuery<Exchange> query = entityManager.createNamedQuery(Exchange.FIND_EXCHANGE_BY_CURRENCIES, Exchange.class);
    query.setParameter("sourceCurrency", sourceCurrency);
    query.setParameter("targetCurrency", targetCurrency);
    return query.getSingleResult();
  }


  @Override
  public <S extends Exchange> S save(S s) {
    return null;
  }

  @Override
  public <S extends Exchange> Iterable<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Optional<Exchange> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(Long aLong) {
    return false;
  }

  @Override
  public Iterable<Exchange> findAll() {
    return null;
  }

  @Override
  public Iterable<Exchange> findAllById(Iterable<Long> iterable) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(Long aLong) {

  }

  @Override
  public void delete(Exchange exchange) {

  }

  @Override
  public void deleteAll(Iterable<? extends Exchange> iterable) {

  }

  @Override
  public void deleteAll() {

  }
}
