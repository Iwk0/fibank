package com.fibank.balance;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface BalanceRepository extends JpaRepository<Balance, Long> {

  Optional<Balance> findByCashierNameAndCurrency(String cashierName, Currency currency);
}
