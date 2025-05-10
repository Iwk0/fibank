package com.fibank.balance;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BalanceReadService {

  private final BalanceRepository balanceRepository;

  public Optional<Balance> findByCashierAndCurrency(String cashierName, Currency currency) {
    return balanceRepository.findByCashierNameAndCurrency(cashierName, currency);
  }
}
