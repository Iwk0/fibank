package com.fibank.exception;

public class NotEnoughBalanceException extends RuntimeException {

  public NotEnoughBalanceException(String message) {
    super(message);
  }
}
