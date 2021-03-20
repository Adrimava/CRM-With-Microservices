package com.ironhack.emofcrmmicroservices.opportunityservice.client;

import com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto.ContactDto;
import com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto.LeadDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("contact-service")
public interface ContactClient {
    @PostMapping("/store-contact")
    ContactDto storeContact(@RequestBody LeadDto leadDto);

}
