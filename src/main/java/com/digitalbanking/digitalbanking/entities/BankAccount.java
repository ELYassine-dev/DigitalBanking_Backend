package com.digitalbanking.digitalbanking.entities;

import com.digitalbanking.digitalbanking.enums.AccountStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type", length=4)
public abstract class BankAccount{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date createdAt;
    @NotNull(message="the balance must not be empty")
    private double balance ;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @NotNull(message="the currency must not be empty")
    private String currency;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.LAZY)
    private List<Operations> operations;
}
