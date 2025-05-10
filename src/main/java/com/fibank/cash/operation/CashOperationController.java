package com.fibank.cash.operation;

import com.fibank.cash.operation.dto.CashOperationRequest;
import com.fibank.cash.operation.dto.CashOperationResponse;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cash-operation")
@RequiredArgsConstructor
public class CashOperationController {

  private final CashOperationService cashOperationService;

  @PostMapping
  public ResponseEntity<CashOperationResponse> cashOperation(
      @Valid @RequestBody CashOperationRequest request) {
    return ResponseEntity.created(URI.create("")).body(cashOperationService.cashOperation(request));
  }
}
