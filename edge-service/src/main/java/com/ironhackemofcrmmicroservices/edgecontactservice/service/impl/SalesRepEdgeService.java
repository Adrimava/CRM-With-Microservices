package com.ironhackemofcrmmicroservices.edgecontactservice.service.impl;

import com.ironhackemofcrmmicroservices.edgecontactservice.client.SalesRepClient;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.SalesRepDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.service.interfaces.ISalesRepEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SalesRepEdgeService implements ISalesRepEdgeService {

    @Autowired
    SalesRepClient salesRepClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    private SalesRepDto salesRepFallBack() {
        return new SalesRepDto(null);
    }

    private List<SalesRepDto> allSalesRepFallBack() {
        return null;
    }

    public SalesRepDto storeSalesRep(SalesRepDto salesRepDTO) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("salesRep-service");

        SalesRepDto newSalesRepDTO = circuitBreaker.run(() -> salesRepClient.storeSalesRep(salesRepDTO),
                throwable -> salesRepFallBack());

        return newSalesRepDTO;
    }

    public SalesRepDto getSalesRep(Integer id) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("salesRep-service");

        SalesRepDto salesRepDTO = circuitBreaker.run(() -> salesRepClient.getSalesRep(id),
                throwable -> salesRepFallBack());

        return salesRepDTO;
    }

    public List<SalesRepDto> getAllSalesRep() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("salesRep-service");

        List<SalesRepDto> salesRepDTOList = circuitBreaker.run(() -> salesRepClient.getAllSalesRep(),
                throwable -> allSalesRepFallBack());

        return salesRepDTOList;
    }

    public void associateLeadToSalesRep(Integer salesRepId, Integer leadId) {

        try {
            salesRepClient.associateLeadToSalesRep(salesRepId, leadId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public void associateOppToSalesRep(Integer salesRepId, Integer oppId) {

        try {
            salesRepClient.associateOppToSalesRep(salesRepId, oppId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
