package com.fibank.cashier;

import org.springframework.data.jpa.repository.JpaRepository;

interface CashierRepository extends JpaRepository<Cashier, Long> {
}
