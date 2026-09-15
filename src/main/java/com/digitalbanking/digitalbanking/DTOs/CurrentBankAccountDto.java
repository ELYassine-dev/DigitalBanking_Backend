package com.digitalbanking.digitalbanking.DTOs;

import com.digitalbanking.digitalbanking.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentBankAccountDto extends BankAccountDto{
    private Double balance ;
    private String currency;
    private String id;
    private Date createdAt;
     private AccountStatus status;
     private ReqCustomerDto  reqCustomerDto;
     private double overDraft;





}
