package com.digitalbanking.digitalbanking.Services;

import com.digitalbanking.digitalbanking.DTOs.*;
import com.digitalbanking.digitalbanking.entities.*;
import com.digitalbanking.digitalbanking.enums.AccountStatus;
import com.digitalbanking.digitalbanking.enums.OperationType;
import com.digitalbanking.digitalbanking.exception.BalanceNotSufficientException;
import com.digitalbanking.digitalbanking.exception.BankAccountNotFoundException;
import com.digitalbanking.digitalbanking.exception.CustomerNotFoundException;
import com.digitalbanking.digitalbanking.mappers.BankAccountMapper;
import com.digitalbanking.digitalbanking.repositories.BankAccountRepository;
import com.digitalbanking.digitalbanking.repositories.CustomerRepository;
import com.digitalbanking.digitalbanking.repositories.OperationsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {
    private CustomerRepository customerRepository;
    private BankAccountRepository  bankAccountRepository;
    private OperationsRepository operationsRepository;
    private BankAccountMapper maperdto;

//    private static final Logger log= LoggerFactory.getLogger(BankAccountServiceImpl.class);


    @Override
    public ReqCustomerDto saveCustomer(ReqCustomerDto customerdto) {
        log.info("Saving new customer");
        Customer customer=maperdto.fromReqCustomerDto(customerdto);
        Customer savedCustomer=customerRepository.save(customer);

        return maperdto.fromCustomer(savedCustomer);
    }




    @Override
    public CurrentBankAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) {
        Customer customer= customerRepository.findById(customerId).orElse(null);
        if(customer == null){
            throw new CustomerNotFoundException("Customer not found");
        }
        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCurrency("dh");
        currentAccount.setCustomer(customer);
        currentAccount.setStatus(AccountStatus.CREATED);
        currentAccount.setOverDraft(overDraft);

       CurrentAccount saveAccount= bankAccountRepository.save(currentAccount);
        return maperdto.fromCurrentAccount(saveAccount);
    }



    @Override
    public SavingBankAccountDto saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) {
        Customer customer= customerRepository.findById(customerId).orElse(null);
        if(customer == null){
            throw new CustomerNotFoundException("Customer not found");
        }
     SavingAccount savingaccount = new SavingAccount();
        savingaccount.setBalance(initialBalance);
        savingaccount.setCustomer(customer);
        savingaccount.setCurrency("dh");
        savingaccount.setStatus(AccountStatus.CREATED);
        savingaccount.setIntersetRate(interestRate);
        savingaccount.setCreatedAt((new Date()));

        SavingAccount saveAccount= bankAccountRepository.save(savingaccount);
        return maperdto.fromSavingAccount(saveAccount);
    }


    @Override
    public List<ReqCustomerDto> listCustomers() {
       List<Customer> customer= customerRepository.findAll() ;
       List<ReqCustomerDto> reqcustomerdto= customer.stream().map(customers->
                maperdto.fromCustomer(customers)).toList();
       return reqcustomerdto;

    }

    @Override
    public BankAccountDto getBankAccount(String accountid) {
        BankAccount bankAccount=bankAccountRepository.findById(accountid).orElse(null);

        if(bankAccount instanceof SavingAccount){
            SavingAccount savingAccount=(SavingAccount)bankAccount;
            return maperdto.fromSavingAccount(savingAccount);
        }else{
         CurrentAccount currentAccount=(CurrentAccount)bankAccount;
         return maperdto.fromCurrentAccount(currentAccount);
             }
    }


    @Override
    public void credit(String accountid, double amount, String description) {

        BankAccount bankaccount=bankAccountRepository.findById(accountid).orElse(null);
        Operations oper=new Operations();
        oper.setOperationType(OperationType.CREDIT);
        oper.setAmount(amount);
        oper.setDescription(description);
        oper.setDate((new Date()));

        oper.setBankAccount(bankaccount);
        operationsRepository.save(oper);
        bankaccount.setBalance(bankaccount.getBalance()+amount);
        bankAccountRepository.save(bankaccount);



    }

    @Override
    public void debit(String accountid, double amount, String description) {

        BankAccount bankaccount=bankAccountRepository.findById(accountid).orElse(null);
        if(bankaccount.getBalance()<amount)
            throw new BalanceNotSufficientException(" balance not sufficient");
        Operations oper=new Operations();
        oper.setOperationType(OperationType.DEBIT);
        oper.setAmount(amount);
        oper.setDescription(description);
        oper.setDate((new Date()));

        oper.setBankAccount(bankaccount);
        operationsRepository.save(oper);
        bankaccount.setBalance(bankaccount.getBalance()-amount);
        bankAccountRepository.save(bankaccount);



    }

    @Override
    public void transfer(String sourceAccountId, String destinationAccountId, double amount)throws BankAccountNotFoundException {

        debit(sourceAccountId, amount, "transfer to "+destinationAccountId);
        credit(destinationAccountId, amount, "transfer from "+sourceAccountId);

    }

    @Override
    public List<BankAccountDto> listofBankAccount(){
        List<BankAccount> bankaccounts= bankAccountRepository.findAll();
      List<BankAccountDto>  bankaccountdto= bankaccounts.stream().map(bank->{
            if(bank instanceof SavingAccount ){
                SavingAccount savingAccounts=(SavingAccount) bank;
                return maperdto.fromSavingAccount(savingAccounts);
            }else{
               CurrentAccount current=(CurrentAccount) bank;
               return maperdto.fromCurrentAccount(current);
            } }
                ).toList();
        return bankaccountdto;
    }




@Override
public ReqCustomerDto getCustomer(Long id){
        Customer customer=customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
        return  maperdto.fromCustomer(customer);
    }

@Override
public ReqCustomerDto updateCustomer(ReqCustomerDto customerdto){
        log.info("Updating customer");
        Customer customer=maperdto.fromReqCustomerDto(customerdto);
        Customer updatedcustomer = customerRepository.save(customer);
        return maperdto.fromCustomer(updatedcustomer);
}

@Override
public void deletecustomer(Long id){
        customerRepository.deleteById(id);
}


@Override
public List<OperationsDto>  accountHistory(String id){
      List<Operations> operations= operationsRepository.findByBankAccountId(id);

      return operations.stream().map(oper->
              maperdto.fromOperations(oper)).toList();
}

    @Override
    public AccountHistoryDto getAccountHistory(String id, int page, int size) {
     BankAccount bankaccount=bankAccountRepository.findById(id).orElse(null);
        if(bankaccount==null) throw new BankAccountNotFoundException("Account not found");

       Page<Operations> accountoperations= operationsRepository.findByBankAccountId(id, PageRequest.of(page,size));
AccountHistoryDto accountHistoryDto=new AccountHistoryDto();
List<OperationsDto> operationdto=accountoperations.getContent().stream().map(op->maperdto.fromOperations(op)).toList();
        accountHistoryDto.setOperationsdto(operationdto);
        accountHistoryDto.setAccountid(bankaccount.getId());
        accountHistoryDto.setBalance(bankaccount.getBalance());
        accountHistoryDto.setCurrentPage(page);
        accountHistoryDto.setSize(size);
        accountHistoryDto.setTotalPage(accountoperations.getTotalPages());

        return accountHistoryDto;
    }


}
