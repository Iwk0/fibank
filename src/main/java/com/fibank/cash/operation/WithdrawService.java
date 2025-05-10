package com.fibank.cash.operation;

import com.fibank.balance.Balance;
import com.fibank.balance.BalanceCommandService;
import com.fibank.balance.BalanceReadService;
import com.fibank.cash.operation.dto.CashOperationRequest;
import com.fibank.exception.NotEnoughBalanceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class WithdrawService implements OperationService {

  private final BalanceReadService balanceReadService;
  private final BalanceCommandService balanceCommandService;

  @Override
  public Balance cashOperation(CashOperationRequest request) {
    log.info("Executing withdraw operation");

    Balance balance =
        balanceReadService
            .findByCashierAndCurrency(request.getCashier(), request.getCurrency())
            .orElseThrow();

    request
        .getDenominations()
        .forEach(
            (key, value) ->
                balance
                    .getDenominations()
                    .merge(
                        key,
                        value,
                        (a, b) -> {
                          int subtraction = a - b;

                          if (subtraction < 0) {
                            log.error("Not enough subtraction");
                            throw new NotEnoughBalanceException("Not enough denominations");
                          }

                          return subtraction;
                        }));

    int calculated =
        request.getDenominations().entrySet().stream()
            .mapToInt(entry -> entry.getKey() * entry.getValue())
            .sum();

    balance.setAmount(balance.getAmount() - calculated);

    return balanceCommandService.save(balance);
  }
}
