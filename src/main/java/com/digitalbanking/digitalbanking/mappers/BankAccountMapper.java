package com.digitalbanking.digitalbanking.mappers;

import com.digitalbanking.digitalbanking.DTOs.CurrentBankAccountDto;
import com.digitalbanking.digitalbanking.DTOs.OperationsDto;
import com.digitalbanking.digitalbanking.DTOs.ReqCustomerDto;
import com.digitalbanking.digitalbanking.DTOs.SavingBankAccountDto;
import com.digitalbanking.digitalbanking.entities.CurrentAccount;
import com.digitalbanking.digitalbanking.entities.Customer;
import com.digitalbanking.digitalbanking.entities.Operations;
import com.digitalbanking.digitalbanking.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class BankAccountMapper {

 public ReqCustomerDto fromCustomer(Customer customer){
     ReqCustomerDto reqCustomerDto = new ReqCustomerDto();
     BeanUtils.copyProperties(customer,reqCustomerDto);
     return reqCustomerDto;
 }

 public Customer fromReqCustomerDto(ReqCustomerDto reqCustomerDto){
     Customer customer = new Customer();
     BeanUtils.copyProperties(reqCustomerDto,customer);
     return customer;
 }
//------------------------------------------------------

 public SavingBankAccountDto fromSavingAccount(SavingAccount savingAccount){
     SavingBankAccountDto savingBankAccountDto =
             new SavingBankAccountDto();

     BeanUtils.copyProperties(savingAccount,savingBankAccountDto);
     savingBankAccountDto.setReqCustomerDto(fromCustomer( savingAccount.getCustomer()));
  savingBankAccountDto.setType(savingAccount.getClass().getSimpleName());
     return savingBankAccountDto;
 }

 public SavingAccount fromSavingBankAccountDto(SavingBankAccountDto savingBankAccountDto){
     SavingAccount savingAccount = new SavingAccount();
     BeanUtils.copyProperties(savingBankAccountDto,savingAccount);
     savingAccount.setCustomer(fromReqCustomerDto(savingBankAccountDto.getReqCustomerDto()));

     return savingAccount;
 }
// -----------------------------------------------------------

    public CurrentBankAccountDto fromCurrentAccount(CurrentAccount currentAccount){
     CurrentBankAccountDto currentBankAccountDto = new CurrentBankAccountDto();
     BeanUtils.copyProperties(currentAccount,currentBankAccountDto);
     currentBankAccountDto.setReqCustomerDto(fromCustomer( currentAccount.getCustomer()));
    currentBankAccountDto.setType(currentAccount.getClass().getSimpleName());
     return currentBankAccountDto;
    }

    public CurrentAccount fromCurrentBankAccountDto(CurrentBankAccountDto currentBankAccountDto){
     CurrentAccount currentAccount = new CurrentAccount();
     BeanUtils.copyProperties(currentBankAccountDto,currentAccount);
     currentAccount.setCustomer(fromReqCustomerDto(currentBankAccountDto.getReqCustomerDto()));
     return currentAccount;
    }

//-----------------------------------------------------------------------

    public OperationsDto fromOperations(Operations operations){
          OperationsDto operationsdto=new OperationsDto();
          BeanUtils.copyProperties(operations,operationsdto);
     return operationsdto;
    }

 public Operations fromOperationsDto(OperationsDto operationsDto){
     Operations operations=new Operations();
     BeanUtils.copyProperties(operationsDto,operations);
     return operations;

 }








}


//second way professional to mapper using mapStruct

//@Mapper(componentModel = "spring")
//public interface CustomerMapper {
//
//    CustomerDto fromCustomer(Customer customer);
//
//    Customer fromDto(CustomerDto dto);
//}