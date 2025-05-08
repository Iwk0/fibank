package com.fibank.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionIdGenerator {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  public static String generateTransactionId() {
    String timestamp = LocalDateTime.now().format(FORMATTER);
    String shortUUID = UUID.randomUUID().toString().substring(0, 8);
    return "TXN-" + timestamp + "-" + shortUUID;
  }
}
