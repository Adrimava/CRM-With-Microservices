package com.ironhackemofcrmmicroservices.edgecontactservice.service.interfaces;


import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.AccountDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.UpdateAccountDto;

import java.util.List;

public interface IAccountEdgeService {
    AccountDto showAccount(Integer id);

    List<AccountDto> showAccounts();

    AccountDto storeAccount(AccountDto accountDTO);

    void updateAccount(UpdateAccountDto updateAccountDTO);
}
