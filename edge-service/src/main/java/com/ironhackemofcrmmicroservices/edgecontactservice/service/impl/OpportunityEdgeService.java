package com.ironhackemofcrmmicroservices.edgecontactservice.service.impl;

import com.ironhackemofcrmmicroservices.edgecontactservice.client.OpportunityClient;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.CloseOpportunityDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.ConvertOpportunityDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.OpportunityDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.service.interfaces.IOpportunityEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OpportunityEdgeService implements IOpportunityEdgeService {

    @Autowired
    private OpportunityClient opportunityClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();


    private CircuitBreaker oppCircuitBreaker = circuitBreakerFactory.create("opportunity-service");

    public OpportunityDto getOpportunity(Integer id) {
        return oppCircuitBreaker.run(() -> opportunityClient.getOpportunity(id), throwable -> getOpportunityFallback());
    }

    private OpportunityDto getOpportunityFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public void convertOpportunity(ConvertOpportunityDto convertOpportunityDto) {
        opportunityClient.convertOpportunity(convertOpportunityDto);
    }

    public void closeOpportunity(CloseOpportunityDto closeOpportunityDto) {
        opportunityClient.closeOpportunity(closeOpportunityDto);
    }

}
