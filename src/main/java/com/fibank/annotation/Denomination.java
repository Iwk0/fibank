package com.fibank.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = {DenominationValidator.class})
@Documented
public @interface Denomination {

  String message() default "Invalid denomination";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
