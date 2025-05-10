package com.fibank.cash.balance.dto;

import com.fibank.balance.Currency;
import com.fibank.balance.Operation;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CashBalanceResponse {

  private String transactionId;
  private LocalDateTime timestamp;
  private Integer amount;
  private Currency currency;
  private Operation operation;
  private String cashier;
}
