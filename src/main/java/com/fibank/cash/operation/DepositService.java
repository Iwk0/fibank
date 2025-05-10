package com.fibank.cash.operation;

import com.fibank.balance.Balance;
import com.fibank.balance.BalanceCommandService;
import com.fibank.balance.BalanceReadService;
import com.fibank.cash.operation.dto.CashOperationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class DepositService implements OperationService {

  private final BalanceReadService balanceReadService;
  private final BalanceCommandService balanceCommandService;

  @Override
  public Balance cashOperation(CashOperationRequest request) {
    log.info("Executing deposit operation");

    Balance balance =
        balanceReadService
            .findByCashierAndCurrency(request.getCashier(), request.getCurrency())
            .orElseThrow();

    int calculated =
        request.getDenominations().entrySet().stream()
            .mapToInt(entry -> entry.getKey() * entry.getValue())
            .sum();

    balance.setAmount(balance.getAmount() + calculated);

    request
        .getDenominations()
        .forEach((key, value) -> balance.getDenominations().merge(key, value, Integer::sum));

    return balanceCommandService.save(balance);
  }
}
