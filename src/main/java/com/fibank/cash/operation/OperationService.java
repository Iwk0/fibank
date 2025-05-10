package com.fibank.cash.operation;

import com.fibank.balance.Balance;
import com.fibank.cash.operation.dto.CashOperationRequest;

public interface OperationService {

  /**
   * Processes a cash operation for a given cashier and currency.
   *
   * @param request cash operation request
   * @param balance current balance
   */
  void cashOperation(CashOperationRequest request, Balance balance);
}
