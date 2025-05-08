package com.fibank.cashier;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CashierCommandService {

  private final CashierRepository cashierRepository;

  public Cashier createCashier(Cashier cashier) {
    return cashierRepository.save(cashier);
  }
}
