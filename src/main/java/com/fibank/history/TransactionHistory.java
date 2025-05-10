package com.fibank.history;

import com.fibank.balance.Currency;
import com.fibank.balance.Operation;
import com.fibank.util.TransactionIdGenerator;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TransactionHistory {

  @Builder.Default private String transactionId = TransactionIdGenerator.generateTransactionId();

  @Builder.Default private Instant timestamp = Instant.now();

  private Integer amount;
  private Currency currency;
  private Operation operation;
  private String cashier;

  @Override
  public String toString() {
    return String.format(
        "transactionId: '%s' | timestamp: '%s' | amount: %d | currency: %s | operation: %s | cashier: '%s'",
        transactionId,
        timestamp,
        amount,
        currency != null ? currency.name() : "N/A",
        operation != null ? operation.name() : "N/A",
        cashier);
  }
}
