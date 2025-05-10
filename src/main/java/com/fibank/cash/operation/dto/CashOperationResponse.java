package com.fibank.cash.operation.dto;

import com.fibank.balance.Currency;
import com.fibank.balance.Operation;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CashOperationResponse {

  private String cashierName;
  private Integer amount;
  private Currency currency;
  private Operation operation;
  private Map<Integer, Integer> denominations;
}
