package com.fibank.annotation;

import com.fibank.cashier.CashierReadService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CashierExists implements ConstraintValidator<Exists, String> {

  private final CashierReadService cashierReadService;

  @Override
  public boolean isValid(String name, ConstraintValidatorContext ctx) {
    return cashierReadService.exists(name);
  }
}
