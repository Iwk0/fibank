package com.fibank.cash.operation;

import com.fibank.balance.Balance;
import com.fibank.balance.BalanceCommandService;
import com.fibank.balance.BalanceReadService;
import com.fibank.balance.Operation;
import com.fibank.cash.operation.dto.CashOperationRequest;
import com.fibank.cash.operation.dto.CashOperationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CashOperationService {

  private final BalanceReadService balanceReadService;
  private final BalanceCommandService balanceCommandService;

  public CashOperationResponse cashOperation(CashOperationRequest request) {
    Balance balance =
        balanceReadService
            .findByCashierAndCurrency(request.getCashier(), request.getCurrency())
            .orElseThrow();

    if (request.getOperation() == Operation.WITHDRAW) {
      log.info("Executing withdraw operation");
    } else {
      log.info("Executing deposit operation");

      int calculated =
          request.getDenominations().entrySet().stream()
              .mapToInt(entry -> entry.getKey() * entry.getValue())
              .sum();

      balance.setAmount(balance.getAmount() + calculated);

      request
          .getDenominations()
          .forEach(
              (key, value) ->
                  balance.getDenominations().computeIfPresent(key, (k, v) -> v + value));

      Balance persistedBalance = balanceCommandService.save(balance);

      return CashOperationResponse.builder()
          .cashierName(persistedBalance.getCashier().getName())
          .amount(persistedBalance.getAmount())
          .currency(persistedBalance.getCurrency())
          .operation(request.getOperation())
          .build();
    }

    return null;
  }
}
