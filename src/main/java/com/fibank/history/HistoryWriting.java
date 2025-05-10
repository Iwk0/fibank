package com.fibank.history;

import com.fibank.cash.operation.dto.CashOperationResponse;
import com.fibank.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class HistoryWriting {

  @Value("${files.transaction-history.path}")
  private String filePath;

  @AfterReturning(
      value = "execution(* com.fibank.cash.operation.CashOperationService.cashOperation(..))",
      returning = "result")
  public void writeTransactionHistory(JoinPoint joinPoint, CashOperationResponse result) {
    TransactionHistory transactionHistory =
        TransactionHistory.builder()
            .amount(result.getAmount())
            .currency(result.getCurrency())
            .cashier(result.getCashierName())
            .operation(result.getOperation())
            .build();

    FileUtil.writeToFile(transactionHistory.toString(), filePath);

    log.info("Transaction history created successfully");
  }
}
