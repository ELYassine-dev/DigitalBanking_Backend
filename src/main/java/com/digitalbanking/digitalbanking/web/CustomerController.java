package com.digitalbanking.digitalbanking.web;

import com.digitalbanking.digitalbanking.DTOs.ReqCustomerDto;
import com.digitalbanking.digitalbanking.Services.BankAccountService;
import com.digitalbanking.digitalbanking.entities.Customer;
import com.digitalbanking.digitalbanking.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@Slf4j
public class CustomerController {
    private BankAccountService bankAccountService;


    @GetMapping
    public List<ReqCustomerDto> getCustomer() {
        return bankAccountService.listCustomers();
    }

    @GetMapping("/{id}")
    public ReqCustomerDto getCustomer(@PathVariable Long id ) {
    return bankAccountService.getCustomer(id);
    }

    @PostMapping
    public ReqCustomerDto addCustomer(@RequestBody ReqCustomerDto customerDto) {
       return  bankAccountService.saveCustomer(customerDto);
    }

    @PutMapping("/{id}")
    public ReqCustomerDto updateCustomer(@RequestBody ReqCustomerDto customerDto,
                                         @PathVariable Long id ) {

        customerDto.setId(id);
        return bankAccountService.updateCustomer(customerDto);

    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id ) {
        bankAccountService.deletecustomer(id);
    }





}
