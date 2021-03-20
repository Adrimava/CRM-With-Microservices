package com.ironhack.emofcrmmicroservices.accountmicroservice.service.interfaces;

import com.ironhack.emofcrmmicroservices.accountmicroservice.controller.dto.*;

import java.util.*;

public interface IAccountService {
    AccountDto showAccount(Integer id);
    List<AccountDto> showAccounts();
    AccountDto storeAccount(AccountDto accountDTO);
    void updateAccount(UpdateAccountDto updateAccountDto);
    List<OppsByIndustryDto> getOppsByIndustry();
    List<OppsByCityDto> getOppCountByCity();
    List<OppsByCountryDto> getOppsByCountry();
    Double getMeanOppsPerAccount();
    Integer getMaxOppsPerAccount();
    Integer getMinOppsPerAccount();
    Double getMedianOppsPerAccount();
    Double getMeanEmployeeCount();
    Integer getMaxEmployeeCount();
    Integer getMinEmployeeCount();
    Double getMedianEmployeeCount();
}
