package com.fibank.cashier;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CashierReadService {

    private final CashierRepository cashierRepository;

    public Cashier getCashierById(Long id) {
        return cashierRepository.findById(id).orElseThrow();
    }
}
