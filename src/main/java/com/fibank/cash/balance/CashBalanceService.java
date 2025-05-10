package com.fibank.cash.balance;

import com.fibank.cash.balance.dto.CashBalanceResponse;
import com.fibank.history.TransactionHistoryService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashBalanceService {

  private final TransactionHistoryService transactionHistoryService;

  public List<CashBalanceResponse> cashBalance(
      LocalDateTime dateFrom, LocalDateTime dateTo, String cashier) {
    return transactionHistoryService.parseTransactions(dateFrom, dateTo, cashier).stream()
        .map(
            transactionHistory ->
                CashBalanceResponse.builder()
                    .transactionId(transactionHistory.getTransactionId())
                    .operation(transactionHistory.getOperation())
                    .currency(transactionHistory.getCurrency())
                    .timestamp(transactionHistory.getTimestamp())
                    .amount(transactionHistory.getAmount())
                    .cashier(transactionHistory.getCashier())
                    .build())
        .toList();
  }
}
