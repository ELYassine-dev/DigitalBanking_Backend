package com.digitalbanking.digitalbanking.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class AccountHistoryDto {

    private String accountid;
    private double balance;
    private int currentPage;
    private int totalPage;
    private int size;
    private List<OperationsDto> operationsdto;


}
