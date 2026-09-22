package com.digitalbanking.digitalbanking.Services;

import com.digitalbanking.digitalbanking.DTOs.*;
import com.digitalbanking.digitalbanking.entities.BankAccount;
import com.digitalbanking.digitalbanking.entities.CurrentAccount;
import com.digitalbanking.digitalbanking.entities.Customer;
import com.digitalbanking.digitalbanking.entities.SavingAccount;

import java.util.List;

public interface BankAccountService {

    ReqCustomerDto saveCustomer(ReqCustomerDto reqCustomerDto);


    CurrentBankAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId);
    SavingBankAccountDto saveSavingBankAccount(double initialBalance, double interestRate, Long customerId);

    List<ReqCustomerDto> listCustomers();
    BankAccountDto getBankAccount(String accountid);
    void credit(String accountid, double amount,String description);

    void debit(String accountid, double amount,String description);


    void transfer(String sourceAccountId,String destinationAccountId,double amount,String description);


    List<BankAccountDto> listofBankAccount();

    ReqCustomerDto getCustomer(Long id);

    ReqCustomerDto updateCustomer(ReqCustomerDto customerdto);

    void deletecustomer(Long id);


    List<OperationsDto>  accountHistory(String id);

    AccountHistoryDto getAccountHistory(String id, int page, int size);

    List<ReqCustomerDto> searchcustomer(String searchkw);
}
