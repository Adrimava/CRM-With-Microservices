package com.ironhack.emofcrmmicroservices.opportunityservice.client;

import com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto.SalesRepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("salesRep-service")
public interface SalesRepClient {
    @GetMapping("/get-salesrep/{id}")
    SalesRepDto getSalesRep(@PathVariable Integer id);

    @PutMapping("/salesrep-opp/{salesRepId}/{oppId}")
    void associateOppToSalesRep(@PathVariable Integer salesRepId, @PathVariable Integer oppId);
}
