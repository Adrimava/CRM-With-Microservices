package com.ironhackemofcrmmicroservices.edgecontactservice.service.impl;

import com.ironhackemofcrmmicroservices.edgecontactservice.client.ContactClient;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.ContactDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.LeadDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.service.interfaces.IContactEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContactEdgeService implements IContactEdgeService {

    @Autowired
    private ContactClient contactClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public ContactDto storeContact(LeadDto leadDto) {

        CircuitBreaker contactCircuitBreaker = circuitBreakerFactory.create("contact-service");

        return contactCircuitBreaker.run(() -> contactClient.storeContact(leadDto),
                throwable -> storeContactFallBack(leadDto));
    }

    private ContactDto storeContactFallBack(LeadDto leadDto) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The contact service is currently unavailable. Please, try again.");
    }

}
