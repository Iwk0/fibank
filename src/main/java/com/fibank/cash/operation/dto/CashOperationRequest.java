package com.fibank.cash.operation.dto;

import com.fibank.annotation.Denomination;
import com.fibank.annotation.Exists;
import com.fibank.balance.Currency;
import com.fibank.balance.Operation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Denomination
public class CashOperationRequest {

  @Exists @NotBlank private String cashier;

  @NotNull private Currency currency;

  @NotNull private Operation operation;

  @NotNull private Integer amount;

  @NotEmpty private Map<Integer, Integer> denominations;
}
