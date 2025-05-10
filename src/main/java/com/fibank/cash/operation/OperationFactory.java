package com.fibank.cash.operation;

import com.fibank.cash.Operation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationFactory {

  public static OperationService getOperation(Operation operation) {
    return switch (operation) {
      case DEPOSIT -> new DepositService();
      case WITHDRAW -> new WithdrawService();
    };
  }
}
