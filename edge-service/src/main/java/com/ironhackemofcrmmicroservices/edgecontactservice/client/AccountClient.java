package com.ironhackemofcrmmicroservices.edgecontactservice.client;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("account-service")
public interface AccountClient {

    @GetMapping("/get-account/{id}")
    AccountDto showAccount(@PathVariable Integer id);

    @GetMapping("/get-account")
    List<AccountDto> showAccounts();

    @PostMapping("/store-account")
    @ResponseStatus(HttpStatus.CREATED)
    AccountDto storeAccount(@RequestBody @Valid AccountDto accountDTO);

    @PutMapping("/update-account")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateAccount(@RequestBody @Valid UpdateAccountDto updateAccountDTO);

    @GetMapping("/report/opps-by-industry")
    @ResponseStatus(HttpStatus.OK)
    public List<OppsByIndustryDto> getOppsByIndustry();


    @GetMapping("/report/opps-by-city")
    public List<OppsByCityDto> getOppCountByCity();

    @GetMapping("/report/opps-by-country")
    List<OppsByCountryDto> getOppsByCountry();

    @GetMapping("/report/opps-per-account/mean")
    Double getMeanOppsPerAccount();

    @GetMapping("/report/opps-per-account/max")
    Integer getMaxOppsPerAccount();

    @GetMapping("/report/opps-per-account/min")
    Integer getMinOppsPerAccount();

    @GetMapping("/report/opps-per-account/median")
    Double getMedianOppsPerAccount();

    @GetMapping("/report/employee-count/mean")
    Double getMeanEmployeeCount();

    @GetMapping("/report/employee-count/max")
    Integer getMaxEmployeeCount();

    @GetMapping("/report/employee-count/min")
    Integer getMinEmployeeCount();

    @GetMapping("/report/employee-count/median")
    Double getMedianEmployeeCount();
}

