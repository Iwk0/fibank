package com.fibank.balance;

import com.fibank.cashier.Cashier;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "balances")
public class Balance {

  @Id
  @Column(nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @Column(nullable = false)
  private Integer amount;

  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private Currency currency;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "cashier_id", nullable = false)
  private Cashier cashier;

  @ElementCollection
  @MapKeyColumn(name = "denomination")
  @Column(name = "quantity")
  @CollectionTable(name = "denominations", joinColumns = @JoinColumn(name = "balance_id"))
  private Map<Integer, Integer> denominations = new HashMap<>();

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Balance balance = (Balance) o;
    return Objects.equals(amount, balance.amount)
        && currency == balance.currency
        && Objects.equals(cashier, balance.cashier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency, cashier);
  }
}
