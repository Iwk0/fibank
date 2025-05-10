package com.fibank.exception;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

@Service
public class ExceptionService {

  public List<Error> errorHandler(List<FieldError> fieldErrors) {
    return fieldErrors.stream()
        .map(
            fieldError ->
                Error.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build())
        .toList();
  }

  public GlobalError errorHandler(String error) {
    return GlobalError.builder().error(error).build();
  }
}
