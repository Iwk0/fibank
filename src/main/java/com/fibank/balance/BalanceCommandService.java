package com.fibank.balance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BalanceCommandService {

  private final BalanceRepository balanceRepository;

  public Balance save(Balance balance) {
    return balanceRepository.save(balance);
  }
}
