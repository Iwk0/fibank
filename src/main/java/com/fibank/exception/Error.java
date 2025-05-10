package com.fibank.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Error {

  String field;
  String message;
}
