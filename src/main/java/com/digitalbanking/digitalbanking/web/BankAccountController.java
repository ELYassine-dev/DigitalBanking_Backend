package com.digitalbanking.digitalbanking.web;

import com.digitalbanking.digitalbanking.DTOs.*;
import com.digitalbanking.digitalbanking.Services.BankAccountService;
import com.digitalbanking.digitalbanking.entities.BankAccount;
import com.digitalbanking.digitalbanking.exception.BalanceNotSufficientException;
import com.digitalbanking.digitalbanking.exception.BankAccountNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankaccounts")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
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


@PostMapping("/debit")
 public DebitDto debit(@RequestBody DebitDto debitDto)throws BankAccountNotFoundException,BalanceNotSufficientException {
  this.bankAccountService.debit(debitDto.getAccountid(),debitDto.getAmount(),debitDto.getDescription());
return debitDto;
}

 @PostMapping("/credit")
 public CreditDto credit(@RequestBody CreditDto creditDto)throws BankAccountNotFoundException,BalanceNotSufficientException {
  this.bankAccountService.credit(creditDto.getAccountid(),creditDto.getAmount(),creditDto.getDescription());
  return creditDto;
 }

 @PostMapping("/transfer")
 public void transfer(@RequestBody TransferRequestDto transferDto)throws BankAccountNotFoundException,BalanceNotSufficientException {
  this.bankAccountService.transfer(transferDto.getAccountSource(),
          transferDto.getAccountDestination(),
          transferDto.getAmount(),transferDto.getDescription());

 }

}
