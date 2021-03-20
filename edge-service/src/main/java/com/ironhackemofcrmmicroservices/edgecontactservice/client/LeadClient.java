package com.ironhackemofcrmmicroservices.edgecontactservice.client;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.LeadDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("lead-service")
public interface LeadClient {

    //TODO supongo que no tengo que poner el responseStatus
    @GetMapping("/get-lead/{id}")
    //@ResponseStatus(HttpStatus.OK)
    public LeadDto getLead(@PathVariable Integer id);

    @GetMapping("/get-leads")
    //@ResponseStatus(HttpStatus.OK)
    public List<LeadDto> getAllLeads();

    @PostMapping("/store-lead")
    //@ResponseStatus(HttpStatus.CREATED)
    public LeadDto storeLead(@RequestBody LeadDto leadDto);

    @DeleteMapping("/delete-lead/{id}")
    //@ResponseStatus(HttpStatus.OK)
    public void deleteLead(@PathVariable Integer id);
}
