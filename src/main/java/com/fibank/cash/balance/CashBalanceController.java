package com.fibank.cash.balance;

import com.fibank.cash.balance.dto.CashBalanceResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cash-balance")
@RequiredArgsConstructor
public class CashBalanceController {

  private final CashBalanceService cashBalanceService;

  @GetMapping
  public ResponseEntity<List<CashBalanceResponse>> cashBalance(
      @RequestParam(required = false) LocalDateTime dateFrom,
      @RequestParam(required = false) LocalDateTime dateTo,
      @RequestParam(required = false) String cashier) {
    return ResponseEntity.ok(cashBalanceService.cashBalance(dateFrom, dateTo, cashier));
  }
}
