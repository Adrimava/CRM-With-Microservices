package com.ironhack.emofcrmmicroservices.accountmicroservice.controller.interfaces;


import com.ironhack.emofcrmmicroservices.accountmicroservice.controller.dto.*;

import java.util.*;

public interface IAccountController {
    AccountDto showAccount(Integer id);
    List<AccountDto> showAccounts();
    AccountDto storeAccount(AccountDto accountDTO);
    void updateAccount(UpdateAccountDto updateAccountDto);
}
