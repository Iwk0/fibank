package com.fibank.cash.operation;

import com.fibank.balance.Balance;
import com.fibank.balance.BalanceCommandService;
import com.fibank.balance.BalanceReadService;
import com.fibank.cash.operation.dto.CashOperationRequest;
import com.fibank.cash.operation.dto.CashOperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CashOperationService {

  private final BalanceReadService balanceReadService;
  private final BalanceCommandService balanceCommandService;

  public CashOperationResponse cashOperation(CashOperationRequest request) {
    OperationService operationService =
        OperationFactory.getOperation(
            request.getOperation(), balanceReadService, balanceCommandService);

    Balance persistedBalance = operationService.cashOperation(request);

    return CashOperationResponse.builder()
        .cashierName(persistedBalance.getCashier().getName())
        .amount(persistedBalance.getAmount())
        .currency(persistedBalance.getCurrency())
        .operation(request.getOperation())
        .denominations(persistedBalance.getDenominations())
        .build();
  }
}
