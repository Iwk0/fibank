package com.fibank.annotation;

import com.fibank.cash.operation.dto.CashOperationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class DenominationValidator implements ConstraintValidator<Denomination, CashOperationRequest> {

  @Override
  public boolean isValid(CashOperationRequest request, ConstraintValidatorContext ctx) {
    Integer amount = request.getAmount();
    if (amount == null) {
      return false;
    }

    ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
        .addPropertyNode("denominations")
        .addConstraintViolation();

    int calculated =
        request.getDenominations().entrySet().stream()
            .filter(entry -> Objects.nonNull(entry.getValue()))
            .mapToInt(entry -> entry.getKey() * entry.getValue())
            .sum();

    return calculated == amount;
  }
}
