package com.digitalbanking.digitalbanking.entities;

import com.digitalbanking.digitalbanking.enums.OperationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
public class Operations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @NotNull(message="the amount must not be empty")
    private double amount;
    @NotBlank(message="the description must not be empty")
    private String description;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @ManyToOne
    private BankAccount bankAccount;

}
