package com.pe.appventas.mscustomerservice.controllers;

import com.pe.appventas.mscustomerservice.dto.AccountDto;
import com.pe.appventas.mscustomerservice.entities.Account;
import com.pe.appventas.mscustomerservice.util.AccountConverter;
import com.pe.appventas.mscustomerservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountConverter accountConverter;

    @GetMapping(value = "account/{id}")
    public ResponseEntity<AccountDto> findAccountById(@PathVariable("id") Long id) {
        Account account = customerService.findAccountById(id);
        return new ResponseEntity<>(accountConverter.convertAccountToDto(account), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "account")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto account) {
        Account newAccount = accountConverter.convertAccountToEntity(account);
        newAccount = customerService.saveAccount(newAccount);
        return new ResponseEntity<>(accountConverter.convertAccountToDto(newAccount), HttpStatus.CREATED);
    }

    @PutMapping(value = "account/{id}")
    public ResponseEntity<AccountDto> createAccount(@PathVariable("id") Long id, @RequestBody AccountDto account) {
        Account accountUpdate = customerService.findAccountById(id);

        Account updatedAccount = accountConverter.map(accountConverter.convertAccountToEntity(account), accountUpdate);
        updatedAccount = customerService.saveAccount(updatedAccount);
        return new ResponseEntity<>(accountConverter.convertAccountToDto(updatedAccount), HttpStatus.OK);
    }

    @DeleteMapping(value = "account/{id}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable("id") Long id) {
        Account account = customerService.findAccountById(id);
        customerService.deleteAccount(account);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
