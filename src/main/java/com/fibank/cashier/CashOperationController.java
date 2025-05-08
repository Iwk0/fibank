package com.fibank.cashier;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cash-operations")
public class CashOperationController {

    @GetMapping
    void test() {
        int a = 23;
    }
}
