package com.fibank.cashier;

import com.fibank.account.Account;
import java.math.BigDecimal;

import com.fibank.history.TransactionHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cash-balances")
@RequiredArgsConstructor
public class CashBalanceController {

  private final CashierReadService cashierReadService;
  private final CashierCommandService cashierCommandService;

  @GetMapping
  public void createCashier() {
    Cashier cashier = new Cashier();
    cashier.setName("John");

    Account account = new Account();
    account.setBalance(BigDecimal.valueOf(2000));
    account.setCurrency("BGN");
    account.setCashier(cashier);

    cashier.getAccounts().add(account);

    TransactionHistory transactionHistory = new TransactionHistory();
    transactionHistory.setAmount(BigDecimal.valueOf(100));
    transactionHistory.setCurrency(TransactionHistory.Currency.BGN);
    transactionHistory.setDeposit(TransactionHistory.Deposit.DEPOSIT);
    transactionHistory.setAccount(account);

    account.getTransactionHistories().add(transactionHistory);

    cashierCommandService.createCashier(cashier);
  }
}
