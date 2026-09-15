package com.digitalbanking.digitalbanking;

import com.digitalbanking.digitalbanking.DTOs.BankAccountDto;
import com.digitalbanking.digitalbanking.DTOs.CurrentBankAccountDto;
import com.digitalbanking.digitalbanking.DTOs.ReqCustomerDto;
import com.digitalbanking.digitalbanking.DTOs.SavingBankAccountDto;
import com.digitalbanking.digitalbanking.Services.BankAccountService;
import com.digitalbanking.digitalbanking.Services.BankAccountServiceImpl;
import com.digitalbanking.digitalbanking.entities.*;
import com.digitalbanking.digitalbanking.enums.AccountStatus;
import com.digitalbanking.digitalbanking.enums.OperationType;
import com.digitalbanking.digitalbanking.repositories.BankAccountRepository;
import com.digitalbanking.digitalbanking.repositories.CustomerRepository;
import com.digitalbanking.digitalbanking.repositories.OperationsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }


//    @Bean
    CommandLineRunner run(BankAccountService bankAccountService, BankAccountServiceImpl bankAccountServiceImpl) {
        return args -> {

            Stream.of("mohamed","noureddine","khadija").forEach(name -> {
                        ReqCustomerDto customer = new ReqCustomerDto();
                        customer.setName(name);
                        customer.setEmail(name + "@gmail.com");
                        customer.setPhone("123456789");
                        customer.setAddress("rabat");
                        bankAccountService.saveCustomer(customer);
                    });

            bankAccountService.listCustomers().forEach(customer -> {

                bankAccountService.saveCurrentBankAccount(Math.random()*12000,9000,customer.getId());
                bankAccountService.saveSavingBankAccount(Math.random()*1000,5.5,customer.getId());
                List<BankAccountDto> bankAccounts=bankAccountService.listofBankAccount();
for(BankAccountDto bankAccountDto:bankAccounts){
        for (int i = 0; i < 10; i++) {

String accountid;
if(bankAccountDto instanceof SavingBankAccountDto){
    accountid=((SavingBankAccountDto)bankAccountDto).getId();
}else{
    accountid=((CurrentBankAccountDto) bankAccountDto).getId();
}
            bankAccountService.credit(accountid,10000+Math.random()*120000,"CREDIT");
            bankAccountService.debit(accountid,1000+Math.random()*9000,"DEBIT");
        }

}

            }
            );

        };
    }




//    @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            OperationsRepository operationsRepository) {
        return args -> {

             Stream.of("yassine","hassan","aicha").forEach(name -> {
                 Customer customer1 = new Customer();
                 customer1.setName(name);
                 customer1.setAddress("sale");
                 customer1.setPhone("061234567");
                 customer1.setEmail(name+"@gmail.com");
                 customerRepository.save(customer1);
             });

             customerRepository.findAll().forEach(customer -> {
                 CurrentAccount caccount= new  CurrentAccount();
                 caccount.setCustomer(customer);
                 caccount.setBalance(Math.random()*90000);
                 caccount.setCurrency("DH");
                 caccount.setStatus(AccountStatus.CREATED);
                 caccount.setCreatedAt(new Date());
                 caccount.setOverDraft(9000);
                 bankAccountRepository.save(caccount);

                 SavingAccount saccount= new  SavingAccount();
                 saccount.setCustomer(customer);
                 saccount.setBalance(Math.random()*90000);
                 saccount.setCurrency("DH");
                 saccount.setStatus(AccountStatus.CREATED);
                 saccount.setCreatedAt(new Date());
                 saccount.setIntersetRate(5.5);
                 bankAccountRepository.save(saccount);
             });

             bankAccountRepository.findAll().forEach(acc->
             {
                 for(int i=0;i<10;i++){
                     Operations oper=new  Operations();
                     oper.setDate(new Date());
                     oper.setAmount(Math.random()*12000);
                     oper.setOperationType(Math.random()>0.5? OperationType.CREDIT:OperationType.DEBIT);
                     oper.setBankAccount(acc);
                     oper.setDescription("votre amount a etet envoyee");
                     operationsRepository.save(oper);


                 }

             }
             );

        };
    }

}
