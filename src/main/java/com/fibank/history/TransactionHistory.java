package com.fibank.history;

import com.fibank.account.Account;
import com.fibank.util.TransactionIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Immutable
@Table(name = "transaction_histories")
@EntityListeners(AuditingEntityListener.class)
public class TransactionHistory {

  @Id
  @Column(length = 36, nullable = false, updatable = false)
  private String transactionId = TransactionIdGenerator.generateTransactionId();

  @CreatedDate
  @Column(nullable = false)
  private Instant timestamp;

  @Column(precision = 19, scale = 2, nullable = false)
  private BigDecimal amount;

  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private Currency currency;

  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private Deposit deposit;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;

  public enum Deposit {
    DEPOSIT,
    WITHDRAW
  }

  public enum Currency {
    BGN,
    EUR
  }
}
