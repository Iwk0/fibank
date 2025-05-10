package com.fibank.cash.operation;

import com.fibank.balance.Balance;
import com.fibank.cash.operation.dto.CashOperationRequest;

public interface OperationService {

  /**
   * Executes a cash operation.
   *
   * @param request the cash operation request
   * @return the updated balance
   */
  Balance cashOperation(CashOperationRequest request);
}
