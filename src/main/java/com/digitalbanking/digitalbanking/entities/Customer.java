package com.digitalbanking.digitalbanking.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    @NotBlank(message = "name must not be empty")
     private String name;
    @NotBlank(message = "email must not be empty")
    private String email;
    @NotNull(message = "phone number must not be empty")
    private String phone;
    @NotBlank(message = "adresse must not be empty")
    private String address;

    @OneToMany(mappedBy = "customer")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<BankAccount> bankAccounts;




}
