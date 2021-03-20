package com.ironhackemofcrmmicroservices.edgecontactservice.service.impl;

import com.ironhackemofcrmmicroservices.edgecontactservice.client.LeadClient;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.LeadDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.service.interfaces.ILeadEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LeadEdgeService implements ILeadEdgeService {

    @Autowired
    private LeadClient leadClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public LeadDto getLead(Integer id) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("lead-service");
        LeadDto leadDto = circuitBreaker.run(() -> leadClient.getLead(id), throwable -> leadFallBack());
        if (leadDto == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Lead server not avaliable");
        }
        return leadDto;
    }

    public List<LeadDto> getAllLeads() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("lead-service");
        List<LeadDto> listLeadDto = circuitBreaker.run(() -> leadClient.getAllLeads(), throwable -> leadListFallBack());
        if (listLeadDto == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Lead server not avaliable");
        }
        return listLeadDto;
    }

    public LeadDto storeLead(LeadDto leadDto) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("lead-service");
        LeadDto leadDto1 = circuitBreaker.run(() -> leadClient.storeLead(leadDto), throwable -> leadFallBack());
        if (leadDto1 == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Lead server not avaliable");
        }
        return leadDto1;
    }

    public void deleteLead(Integer id) {
        try {
            leadClient.deleteLead(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Lead server not avaliable");
        }
    }

    private LeadDto leadFallBack() {
        return null;
    }


    private List<LeadDto> leadListFallBack() {
        return null;
    }

}
