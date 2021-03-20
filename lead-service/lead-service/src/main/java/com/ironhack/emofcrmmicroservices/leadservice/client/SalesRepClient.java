package com.ironhack.emofcrmmicroservices.leadservice.client;

import com.ironhack.emofcrmmicroservices.leadservice.controller.dto.SalesRepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

@FeignClient("salesRep-service")
public interface SalesRepClient {
    @PutMapping("/salesrep-lead/{salesRepId}/{leadId}")
    void associateLeadToSalesRep(@PathVariable Integer salesRepId, @PathVariable Integer leadId);
    @GetMapping("/get-salesrep/{id}")
    SalesRepDTO getSalesrep(@PathVariable Integer id);
}
