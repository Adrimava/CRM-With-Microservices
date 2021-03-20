package com.ironhack.emofcrmmicroservices.accountmicroservice.controller.impl;



import com.ironhack.emofcrmmicroservices.accountmicroservice.controller.dto.*;
import com.ironhack.emofcrmmicroservices.accountmicroservice.controller.interfaces.*;
import com.ironhack.emofcrmmicroservices.accountmicroservice.service.impl.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class AccountController implements IAccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/get-account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto showAccount(@PathVariable Integer id) {
        return accountService.showAccount(id);
    }

    @GetMapping("/get-account")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> showAccounts() {
        return accountService.showAccounts();
    }

    @PostMapping("/store-account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto storeAccount(@RequestBody @Valid AccountDto accountDTO) {
        return accountService.storeAccount(accountDTO);
    }

    @PutMapping("/update-account")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAccount(@RequestBody @Valid UpdateAccountDto updateAccountDto) {
        accountService.updateAccount(updateAccountDto);
    }

    @GetMapping("/report/opps-by-industry")
    @ResponseStatus(HttpStatus.OK)
    public List<OppsByIndustryDto> getOppsByIndustry() {
        return accountService.getOppsByIndustry();
    }

    @GetMapping("/report/opps-by-city")
    public List<OppsByCityDto> getOppCountByCity() {
        return accountService.getOppCountByCity();
    }

    @GetMapping("/report/opps-by-country")
    public List<OppsByCountryDto> getOppsByCountry() {
        return accountService.getOppsByCountry();
    }

    @GetMapping("/report/opps-per-account/mean")
    public Double getMeanOppsPerAccount() {
        return accountService.getMeanOppsPerAccount();
    }

    @GetMapping("/report/opps-per-account/max")
    public Integer getMaxOppsPerAccount() {
        return accountService.getMaxOppsPerAccount();
    }

    @GetMapping("/report/opps-per-account/min")
    public Integer getMinOppsPerAccount() {
        return accountService.getMinOppsPerAccount();
    }

    @GetMapping("/report/opps-per-account/median")
    public Double getMedianOppsPerAccount() {
        return accountService.getMedianOppsPerAccount();
    }

    @GetMapping("/report/employee-count/mean")
    public Double getMeanEmployeeCount() {
        return accountService.getMeanEmployeeCount();
    }

    @GetMapping("/report/employee-count/max")
    public Integer getMaxEmployeeCount() {
        return accountService.getMaxEmployeeCount();
    }

    @GetMapping("/report/employee-count/min")
    public Integer getMinEmployeeCount() {
        return accountService.getMinEmployeeCount();
    }

    @GetMapping("/report/employee-count/median")
    public Double getMedianEmployeeCount() {
        return accountService.getMedianEmployeeCount();
    }

}
