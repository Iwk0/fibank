package com.fibank.cashier;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cash-operations")
@RequiredArgsConstructor
public class CashOperationController {

  private final CashierCommandService cashierCommandService;
}
