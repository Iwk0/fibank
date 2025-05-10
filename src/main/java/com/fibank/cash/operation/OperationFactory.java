package com.fibank.cash.operation;

import com.fibank.balance.BalanceCommandService;
import com.fibank.balance.BalanceReadService;
import com.fibank.cash.Operation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationFactory {

  public static OperationService getOperation(
      Operation operation,
      BalanceReadService balanceReadService,
      BalanceCommandService balanceCommandService) {
    return switch (operation) {
      case DEPOSIT -> new DepositService(balanceReadService, balanceCommandService);
      case WITHDRAW -> new WithdrawService(balanceReadService, balanceCommandService);
    };
  }
}
