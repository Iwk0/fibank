package com.fibank.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = {CashierExists.class})
@Documented
public @interface Exists {

  String message() default "Cashier not found";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
