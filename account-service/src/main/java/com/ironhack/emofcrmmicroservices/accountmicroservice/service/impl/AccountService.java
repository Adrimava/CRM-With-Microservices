package com.ironhack.emofcrmmicroservices.accountmicroservice.service.impl;


import com.ironhack.emofcrmmicroservices.accountmicroservice.controller.dto.*;
import com.ironhack.emofcrmmicroservices.accountmicroservice.enums.*;
import com.ironhack.emofcrmmicroservices.accountmicroservice.model.*;
import com.ironhack.emofcrmmicroservices.accountmicroservice.repository.*;
import com.ironhack.emofcrmmicroservices.accountmicroservice.service.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ContactIdRepository contactIdRepository;

    @Autowired
    private OpportunityIdRepository opportunityIdRepository;

    public AccountDto showAccount(Integer id) {

        Optional<Account> account = accountRepository.findById(id);

        if (account.isPresent()) {

            List<Integer> contacts = new ArrayList<>();

            for (int i=0; i < account.get().getContactList().size(); i++){
                contacts.add(account.get().getContactList().get(i).getContactId());
            }

            List<Integer> opportunities = new ArrayList<>();

            for (int i=0; i < account.get().getOpportunityList().size(); i++){
                opportunities.add(account.get().getOpportunityList().get(i).getOpportunityId());
            }

            return new AccountDto(id, account.get().getIndustry().toString(), account.get().getEmployeeCount(),
                    account.get().getCity(), account.get().getCountry(), contacts, opportunities);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public List<AccountDto> showAccounts() {

        List<Account> accountList = accountRepository.findAll();
        List<AccountDto> accountDTOList = new ArrayList<>();

        for (Account account : accountList) {

            List<Integer> contacts = new ArrayList<>();

            for (int i=0; i < account.getContactList().size(); i++){
                contacts.add(account.getContactList().get(i).getContactId());
            }

            List<Integer> opportunities = new ArrayList<>();

            for (int i=0; i < account.getOpportunityList().size(); i++){
                opportunities.add(account.getOpportunityList().get(i).getOpportunityId());
            }

            accountDTOList.add(new AccountDto(account.getId(), account.getIndustry().toString(), account.getEmployeeCount(), account.getCity(),
                    account.getCountry(), contacts, opportunities));
        }
        return accountDTOList;

    }

    public AccountDto storeAccount(AccountDto accountDTO) {

        List<ContactId> contacts = new ArrayList<>();
        List<OpportunityId> opportunities = new ArrayList<>();

        Account account = new Account();
        account.setIndustry(Industry.valueOf(accountDTO.getIndustry()));
        account.setEmployeeCount(accountDTO.getEmployeeCount());
        account.setCity(accountDTO.getCity());
        account.setCountry(accountDTO.getCountry());
        accountRepository.save(account);

        for (int i=0; i < accountDTO.getContactList().size(); i++){
            ContactId contactId = new ContactId(accountDTO.getContactList().get(i), account);
            contacts.add(contactId);
            contactIdRepository.save(contactId);
        }

        for (int i=0; i < accountDTO.getOpportunityList().size(); i++){
            OpportunityId opportunityId = new OpportunityId(accountDTO.getOpportunityList().get(i), account);
            opportunities.add(opportunityId);
            opportunityIdRepository.save(opportunityId);
        }

        account.setContactList(contacts);
        account.setOpportunityList(opportunities);

        accountRepository.save(account);
        accountDTO.setId(account.getId());
        return accountDTO;
    }

    public void updateAccount(UpdateAccountDto updateAccountDto) {
        Optional<Account> account = accountRepository.findById(updateAccountDto.getAccountId());

        if (account.isPresent()) {

            ContactId contactId = new ContactId(updateAccountDto.getContactId(), account.get());
            OpportunityId opportunityId = new OpportunityId(updateAccountDto.getOpportunityId(), account.get());

            contactIdRepository.save(contactId);
            opportunityIdRepository.save(opportunityId);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public List<OppsByIndustryDto> getOppsByIndustry() {
        List<OpportunityId> oppsId = opportunityIdRepository.findAll();
        HashMap<String, Integer> map = new HashMap<>();
        String industry;
        for (OpportunityId o : oppsId) {
            industry = String.valueOf(o.getAccount().getIndustry());
            if (map.containsKey(industry)) {
                map.put(industry, map.get(industry) + 1);
            } else {
                map.put(industry, 1);
            }
        }
        List<OppsByIndustryDto> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result.add(new OppsByIndustryDto(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    public List<OppsByCityDto> getOppCountByCity() {
        List<Object []> repoList = opportunityIdRepository.getOppCountByCity();
        List<OppsByCityDto> dtoList = new ArrayList<>();
        for(Object[] element: repoList) {
            dtoList.add((new OppsByCityDto((String) element[0], (Integer) element[1])));
        }
        return dtoList;
    }

    public List<OppsByCountryDto> getOppsByCountry() {
        List<Object[]> reportList = accountRepository.getOppsByCountry();
        List<OppsByCountryDto> dtoList = new ArrayList<>();
        for (Object[] element: reportList) {
            dtoList.add(new OppsByCountryDto((String) element[0], (Integer) element[1]));
        }
        return dtoList;
    }

    public Double getMeanOppsPerAccount() {
        List<Object[]> oppsList = opportunityIdRepository.getOppCountByAccount();
        List<Integer> oppCount = new ArrayList<>();
        if (oppsList.size()>0){
            for(Object[] element: oppsList) {
                oppCount.add((Integer) element[1]);
            }

            Integer sum = 0;
            for (Integer count : oppCount){
                sum += count;
            }
            double mean = sum.doubleValue()/oppCount.size();

            return mean;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Integer getMaxOppsPerAccount() {
        List<Object[]> oppsList = opportunityIdRepository.getOppCountByAccount();
        List<Integer> oppCount = new ArrayList<>();
        if (oppsList.size()>0){
            for(Object[] element: oppsList) {
                oppCount.add((Integer) element[1]);
            }
            return Collections.max(oppCount);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Integer getMinOppsPerAccount() {
        List<Object[]> oppsList = opportunityIdRepository.getOppCountByAccount();
        List<Integer> oppCount = new ArrayList<>();
        if (oppsList.size()>0){
            for(Object[] element: oppsList) {
                oppCount.add((Integer) element[1]);
            }
            return Collections.min(oppCount);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Double getMedianOppsPerAccount() {
        List<Object[]> oppsList = opportunityIdRepository.getOppCountByAccount();
        List<Integer> oppCount = new ArrayList<>();
        if (oppsList.size()>0){
            for(Object[] element: oppsList) {
                oppCount.add((Integer) element[1]);
            }

            Collections.sort(oppCount);
            Double median;
            if (oppCount.size() % 2 == 0){
                median = (oppCount.get(oppCount.size() / 2).doubleValue() + oppCount.get(oppCount.size() / 2 - 1).doubleValue())/2;}
            else{
                median = oppCount.get((int) Math.floor(oppCount.size() / 2)).doubleValue();}

            return median;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Double getMeanEmployeeCount() {
        if (accountRepository.findAll().size()>0){
            return accountRepository.findAvgEmployeeCount();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Integer getMaxEmployeeCount() {
        if (accountRepository.findAll().size()>0){
            return accountRepository.findMaxEmployeeCount();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Integer getMinEmployeeCount() {
        if (accountRepository.findAll().size()>0){
            return accountRepository.findMinEmployeeCount();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Double getMedianEmployeeCount() {
        List<Integer> employeeCountList = accountRepository.medianEmployeeCount();
        if (employeeCountList.size()>0){
            Collections.sort(employeeCountList);
            Double median;
            if (employeeCountList.size() % 2 == 0){
                median = (employeeCountList.get(employeeCountList.size() / 2).doubleValue() + employeeCountList.get(employeeCountList.size() / 2 - 1).doubleValue())/2;}
            else{
                median = employeeCountList.get((int) Math.floor(employeeCountList.size() / 2)).doubleValue();}

            return median;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
