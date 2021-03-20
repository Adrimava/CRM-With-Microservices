package com.ironhackemofcrmmicroservices.edgecontactservice.service.impl;

import com.ironhackemofcrmmicroservices.edgecontactservice.client.AccountClient;
import com.ironhackemofcrmmicroservices.edgecontactservice.client.OpportunityClient;
import com.ironhackemofcrmmicroservices.edgecontactservice.client.SalesRepClient;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.*;
import com.ironhackemofcrmmicroservices.edgecontactservice.service.interfaces.IReportEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.*;
import org.springframework.cloud.client.circuitbreaker.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.*;

import java.util.List;

@Service
public class ReportEdgeService implements IReportEdgeService {

    @Autowired
    private SalesRepClient salesRepClient;

    @Autowired
    private OpportunityClient opportunityClient;

    @Autowired
    private AccountClient accountClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public List<LeadsBySalesRepDto> getLeadsBySalesRep() {
        return salesRepClient.getLeadsBySalesRep();
    }

    public List<OppsBySalesRepDto> getOppsBySalesRep() {
        return salesRepClient.getOppsBySalesRep();
    }

    public List<OppsBySalesRepDto> getOppsBySalesRepAndStatus(String status) {
        List<SalesRepDto> salesRepDtoList = salesRepClient.getAllSalesRep();
        return opportunityClient.getOppsBySalesRepAndStatus(salesRepDtoList, status);
    }

    public List<OppsByProductDto> getOppsByProduct() {
        return opportunityClient.getOppsByProduct();
    }

    public List<OppsByProductDto> getOppsByProductAndStatus(String status) {
        return opportunityClient.getOppsByProductAndStatus(status);
    }

    public List<OppsByIndustryDto> getOppsByIndustry() {
        return accountClient.getOppsByIndustry();
    }


    public List<OppsByIndustryDto> getOppsByIndustryAndStatus(String status) {
        List<AccountDto> accounts = accountClient.showAccounts();
        return opportunityClient.getOppsByIndustryAndStatus(status, accounts);
    }

    public List<OppsByCityDto> getOppCountByCity() {
        return accountClient.getOppCountByCity();
    }

    public List<OppsByCityDto> getOppsByCityAndStatus(String status) {
        List<AccountDto> accountDtoList = accountClient.showAccounts();
        return opportunityClient.getOppsByCityAndStatus(accountDtoList, status);
    }

    public List<OppsByCountryDto> getOppsByCountry() {
        return accountClient.getOppsByCountry();
    }

    public List<OppsByCountryDto> getOppsByCountryAndStatus(String status) {
        List<AccountDto> accountDtoList = accountClient.showAccounts();
        return opportunityClient.getOppsByCountryAndStatus(accountDtoList, status);
    }

    private Double accountDoubleFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    private Double accountIntegerFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public Double getMeanOppsPerAccount() {
        CircuitBreaker storeAccountCircuitBreaker = circuitBreakerFactory.create("account-service");
        return storeAccountCircuitBreaker.run(() -> accountClient.getMeanOppsPerAccount(), throwable -> accountDoubleFallback());
    }

    public Integer getMaxOppsPerAccount() {
        CircuitBreaker storeAccountCircuitBreaker = circuitBreakerFactory.create("account-service");
        return (Integer) storeAccountCircuitBreaker.run(() -> accountClient.getMaxOppsPerAccount(), throwable -> accountIntegerFallback());
    }


    public Integer getMinOppsPerAccount() {
        CircuitBreaker storeAccountCircuitBreaker = circuitBreakerFactory.create("account-service");
        return (Integer) storeAccountCircuitBreaker.run(() -> accountClient.getMinOppsPerAccount(), throwable -> accountIntegerFallback());
    }


    public Double getMedianOppsPerAccount() {
        CircuitBreaker storeAccountCircuitBreaker = circuitBreakerFactory.create("account-service");
        return storeAccountCircuitBreaker.run(() -> accountClient.getMedianOppsPerAccount(), throwable -> accountDoubleFallback());
    }


    public Double getMeanEmployeeCount() {
        CircuitBreaker storeAccountCircuitBreaker = circuitBreakerFactory.create("account-service");
        return storeAccountCircuitBreaker.run(() -> accountClient.getMeanEmployeeCount(), throwable -> accountDoubleFallback());
    }

    public Integer getMaxEmployeeCount() {
        CircuitBreaker storeAccountCircuitBreaker = circuitBreakerFactory.create("account-service");
        return (Integer) storeAccountCircuitBreaker.run(() -> accountClient.getMaxEmployeeCount(), throwable -> accountIntegerFallback());
    }


    public Integer getMinEmployeeCount() {
        CircuitBreaker storeAccountCircuitBreaker = circuitBreakerFactory.create("account-service");
        return (Integer) storeAccountCircuitBreaker.run(() -> accountClient.getMinEmployeeCount(), throwable -> accountIntegerFallback());
    }


    public Double getMedianEmployeeCount() {
        CircuitBreaker storeAccountCircuitBreaker = circuitBreakerFactory.create("account-service");
        return storeAccountCircuitBreaker.run(() -> accountClient.getMedianEmployeeCount(), throwable -> accountDoubleFallback());
    }

    private Double opportunityDoubleFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    private Double opportunityIntegerFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public Double getMeanQuantityOrderedProducts() {
        CircuitBreaker storeAccountCircuitBreaker = circuitBreakerFactory.create("opportunity-service");
        return storeAccountCircuitBreaker.run(() -> opportunityClient.getMeanQuantityOrderedProducts(), throwable -> opportunityDoubleFallback());
    }

    public Integer getMaxQuantityOrderedProducts() {
        CircuitBreaker storeAccountCircuitBreaker = circuitBreakerFactory.create("opportunity-service");
        return (Integer) storeAccountCircuitBreaker.run(() -> opportunityClient.getMaxQuantityOrderedProducts(), throwable -> opportunityIntegerFallback());
    }

    public Integer getMinQuantityOrderedProducts() {
        CircuitBreaker storeAccountCircuitBreaker = circuitBreakerFactory.create("opportunity-service");
        return (Integer) storeAccountCircuitBreaker.run(() -> opportunityClient.getMinQuantityOrderedProducts(), throwable -> opportunityIntegerFallback());
    }

    public Double getMedianQuantityOrderedProducts() {
        CircuitBreaker storeAccountCircuitBreaker = circuitBreakerFactory.create("opportunity-service");
        return storeAccountCircuitBreaker.run(() -> opportunityClient.getMedianQuantityOrderedProducts(), throwable -> opportunityDoubleFallback());
    }
}
