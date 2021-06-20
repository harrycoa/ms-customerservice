package com.pe.appventas.mscustomerservice.dto;

import com.pe.appventas.mscustomerservice.util.AccountStatus;
import lombok.Data;

@Data
public class AccountDto {
    private Long id;
    private AddressDto address;
    private CustomerDto customer;
    private CreditCardDto creditCard;
    private AccountStatus status;
}
