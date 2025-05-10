package com.fibank.annotation;

import com.fibank.cash.operation.dto.CashOperationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class DenominationValidator implements ConstraintValidator<Denomination, CashOperationRequest> {

  @Override
  public boolean isValid(CashOperationRequest request, ConstraintValidatorContext ctx) {
    ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
        .addPropertyNode("denominations")
        .addConstraintViolation();

    int calculated =
        request.getDenominations().entrySet().stream()
            .mapToInt(entry -> entry.getKey() * entry.getValue())
            .sum();

    return calculated == request.getAmount();
  }
}
