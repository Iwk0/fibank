package com.fibank.cashier;

import com.fibank.balance.Balance;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;

@Entity
@Getter
@Table(name = "cashiers")
public class Cashier {

  @Id
  @Column(nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 150, nullable = false)
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "cashier")
  private Set<Balance> balances = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Cashier cashier = (Cashier) o;
    return Objects.equals(name, cashier.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }
}
