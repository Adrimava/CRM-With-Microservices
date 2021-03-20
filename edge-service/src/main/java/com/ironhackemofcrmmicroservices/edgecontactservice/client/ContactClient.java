package com.ironhackemofcrmmicroservices.edgecontactservice.client;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.ContactDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.LeadDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@FeignClient("contact-service")
public interface ContactClient {

    @PostMapping("/store-contact")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDto storeContact(@RequestBody @Valid LeadDto leadDto);
}
