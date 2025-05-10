package com.fibank.history;

import com.fibank.balance.Currency;
import com.fibank.balance.Operation;
import com.fibank.util.TransactionIdGenerator;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TransactionHistory {

  @Builder.Default private String transactionId = TransactionIdGenerator.generateTransactionId();

  @Builder.Default
  private LocalDateTime timestamp = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

  @Builder.Default private Map<Integer, Integer> denominations = new HashMap<>();

  private Integer amount;
  private Currency currency;
  private Operation operation;
  private String cashier;

  @Override
  public String toString() {
    return String.format(
        "transactionId: '%s' | timestamp: '%s' | amount: %d | currency: %s | operation: %s | cashier: '%s' | denominations: %s",
        transactionId, timestamp, amount, currency, operation, cashier, formatDenominations());
  }

  private String formatDenominations() {
    if (denominations.isEmpty()) {
      return "{}";
    }

    return denominations.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .map(e -> e.getKey() + "x" + e.getValue())
        .collect(Collectors.joining(", ", "{", "}"));
  }
}
