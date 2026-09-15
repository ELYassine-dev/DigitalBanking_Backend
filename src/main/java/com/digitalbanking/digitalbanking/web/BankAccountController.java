package com.digitalbanking.digitalbanking.web;

import com.digitalbanking.digitalbanking.DTOs.AccountHistoryDto;
import com.digitalbanking.digitalbanking.DTOs.BankAccountDto;
import com.digitalbanking.digitalbanking.DTOs.OperationsDto;
import com.digitalbanking.digitalbanking.Services.BankAccountService;
import com.digitalbanking.digitalbanking.entities.BankAccount;
import com.digitalbanking.digitalbanking.exception.BankAccountNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankaccounts")
@AllArgsConstructor

public class BankAccountController {
 private BankAccountService bankAccountService;



 @GetMapping
 public List<BankAccountDto> listofAccount(){
  return bankAccountService.listofBankAccount();
 }


 @GetMapping("/{id}")
 public BankAccountDto getBankAccount(@PathVariable String id) throws BankAccountNotFoundException {
  return bankAccountService.getBankAccount(id);
 }


 @GetMapping("/{id}/operations")
 public List<OperationsDto>  accountHistories(@PathVariable String id){
  return bankAccountService.accountHistory(id);
 }

 @GetMapping("/{id}/pageoperations")
 public AccountHistoryDto getAccountHistories(@PathVariable String id,
                                              @RequestParam(name = "page",defaultValue= "0") int page,
                                              @RequestParam(name = "size",defaultValue= "5")int size){
  return bankAccountService.getAccountHistory(id,page,size);
 }


// @PostMapping
// public BankAccountDto createBankAccount(@RequestBody BankAccountDto bankAccountDto){
//
//  return null;
// }





}
