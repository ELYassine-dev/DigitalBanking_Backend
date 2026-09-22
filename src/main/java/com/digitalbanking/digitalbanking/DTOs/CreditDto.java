package com.digitalbanking.digitalbanking.DTOs;

import lombok.Data;

@Data
public class CreditDto {
    private String accountid;
    private double amount;
    private String description;

}
