package com.ironhackemofcrmmicroservices.edgecontactservice.client;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.LeadsBySalesRepDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.OppsBySalesRepDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.SalesRepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("salesRep-service")
public interface SalesRepClient {

    @PostMapping("/store-salesrep")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRepDto storeSalesRep(@RequestBody @Valid SalesRepDto salesRepDTO);

    @GetMapping("/get-salesrep/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesRepDto getSalesRep(@PathVariable Integer id);

    @GetMapping("/get-salesrep")
    @ResponseStatus(HttpStatus.OK)
    public List<SalesRepDto> getAllSalesRep();

    @PutMapping("/salesrep-lead/{salesRepId}/{leadId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associateLeadToSalesRep(@PathVariable Integer salesRepId,
                                        @PathVariable Integer leadId);

    @PutMapping("/salesrep-opp/{salesRepId}/{oppId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associateOppToSalesRep(@PathVariable Integer salesRepId,
                                       @PathVariable Integer oppId);

    @GetMapping("/report/leads-by-salesRep")
    List<LeadsBySalesRepDto> getLeadsBySalesRep();

    @GetMapping("/report/opps-by-salesRep")
    List<OppsBySalesRepDto> getOppsBySalesRep();

}
