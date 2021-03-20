package com.ironhack.emofcrmmicroservices.opportunityservice.client;

import com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto.LeadDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("lead-service")
public interface LeadClient {
    @GetMapping("/get-lead/{id}")
    LeadDto getLead(@PathVariable Integer id);

    @DeleteMapping("/delete-lead/{id}")
    void deleteLead(@PathVariable Integer id);

}
