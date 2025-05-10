package com.fibank.exception;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

  private final ExceptionService exceptionService;

  @ExceptionHandler(BindException.class)
  public ResponseEntity<List<Error>> exception(BindException ex) {
    log.error("Exception", ex);

    List<Error> errors = exceptionService.errorHandler(ex.getFieldErrors());

    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<GlobalError> exception(MethodArgumentTypeMismatchException ex) {
    log.error("Exception", ex);

    GlobalError error = exceptionService.errorHandler("Invalid request");

    return ResponseEntity.badRequest().body(error);
  }
}
