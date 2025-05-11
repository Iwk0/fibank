package com.fibank.cash.operation.dto;

import com.fibank.annotation.Denomination;
import com.fibank.annotation.Exists;
import com.fibank.balance.Currency;
import com.fibank.cash.Operation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

  @NotNull @Positive private Integer amount;

  @NotEmpty private Map<@Positive Integer, @Positive Integer> denominations;
}
