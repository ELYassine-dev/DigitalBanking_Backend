package com.digitalbanking.digitalbanking.DTOs;

import com.digitalbanking.digitalbanking.enums.AccountStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class SavingBankAccountDto extends BankAccountDto{
    private Double balance ;
    private String currency;
    private String id;
    private Date createdAt;
     private AccountStatus status;
     private ReqCustomerDto  reqCustomerDto;
     private double interestRate;





}
