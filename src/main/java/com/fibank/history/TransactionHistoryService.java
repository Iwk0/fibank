package com.fibank.history;

import com.fibank.balance.Currency;
import com.fibank.cash.Operation;
import com.fibank.util.FileUtil;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionHistoryService {

  @Value("${files.transaction-history.path}")
  private String filePath;

  public List<TransactionHistory> parseTransactions(
      LocalDateTime dateFrom, LocalDateTime dateTo, String cashier) {
    List<String> lines = FileUtil.readFile(filePath);

    log.info("Read {} lines from file", lines.size());

    return lines.stream()
        .filter(s -> !s.isBlank())
        .map(
            line ->
                Arrays.stream(line.split("\\|"))
                    .map(String::trim)
                    .map(s -> s.split(":", 2))
                    .filter(kv -> kv.length == 2)
                    .collect(
                        Collectors.toMap(
                            kv -> kv[0].trim(), kv -> kv[1].trim().replaceAll("'", ""))))
        .map(
            map ->
                TransactionHistory.builder()
                    .transactionId(map.get("transactionId"))
                    .timestamp(LocalDateTime.parse(map.get("timestamp")))
                    .amount(Integer.valueOf(map.get("amount")))
                    .currency(Currency.valueOf(map.get("currency")))
                    .operation(Operation.valueOf(map.get("operation")))
                    .cashier(map.get("cashier"))
                    .denominations(parseDenominations(map.get("denominations")))
                    .build())
        .filter(filters(dateFrom, dateTo, cashier))
        .toList();
  }

  private Predicate<TransactionHistory> filters(
      LocalDateTime dateFrom, LocalDateTime dateTo, String cashier) {

    Predicate<TransactionHistory> dateFromFilter =
        transactionHistory ->
            dateFrom == null
                || transactionHistory.getTimestamp().equals(dateFrom)
                || transactionHistory.getTimestamp().isAfter(dateFrom);

    Predicate<TransactionHistory> dateToFilter =
        transactionHistory ->
            dateTo == null
                || transactionHistory.getTimestamp().equals(dateTo)
                || transactionHistory.getTimestamp().isBefore(dateTo);

    Predicate<TransactionHistory> cashierFilter =
        transactionHistory -> cashier == null || transactionHistory.getCashier().equals(cashier);

    return dateFromFilter.and(dateToFilter).and(cashierFilter);
  }

  private Map<Integer, Integer> parseDenominations(String denomsRaw) {
    if ("{}".equals(denomsRaw)) {
      return new HashMap<>();
    }

    denomsRaw = denomsRaw.substring(1, denomsRaw.length() - 1);
    String[] entries = denomsRaw.split(",");

    return Arrays.stream(entries)
        .map(e -> e.trim().split("x"))
        .collect(Collectors.toMap(e -> Integer.parseInt(e[0]), e -> Integer.parseInt(e[1])));
  }
}
