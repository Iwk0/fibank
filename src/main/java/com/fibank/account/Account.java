package com.fibank.account;

import com.fibank.cashier.Cashier;
import com.fibank.history.TransactionHistory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "accounts")
public class Account {

  @Id
  @Column(nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(precision = 19, scale = 2, nullable = false)
  private BigDecimal balance;

  @Column(length = 3, nullable = false)
  private String currency;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "cashier_id", nullable = false)
  private Cashier cashier;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
  private Set<TransactionHistory> transactionHistories = new HashSet<>();
}
