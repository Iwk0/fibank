package com.fibank.cash.operation;

import com.fibank.balance.Balance;
import com.fibank.cash.operation.dto.CashOperationRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class DepositService implements OperationService {

  @Override
  public void cashOperation(CashOperationRequest request, Balance balance) {
    log.info("Executing deposit operation");

    int calculated =
        request.getDenominations().entrySet().stream()
            .mapToInt(entry -> entry.getKey() * entry.getValue())
            .sum();

    balance.setAmount(balance.getAmount() + calculated);

    request
        .getDenominations()
        .forEach(
            (key, value) -> balance.getDenominations().computeIfPresent(key, (k, v) -> v + value));
  }
}
