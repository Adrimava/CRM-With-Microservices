package com.ironhack.emofcrmmicroservices.opportunityservice.client;

import com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto.AccountDto;
import com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto.UpdateAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("account-service")
public interface AccountClient {
    @PostMapping("/store-account")
    AccountDto storeAccount(@RequestBody AccountDto accountDto);

    @PutMapping("/update-account")
    void updateAccount(@RequestBody UpdateAccountDto updateAccountDto);
}
