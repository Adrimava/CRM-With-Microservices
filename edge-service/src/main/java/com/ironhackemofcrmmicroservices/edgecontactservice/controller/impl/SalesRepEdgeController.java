package com.ironhackemofcrmmicroservices.edgecontactservice.controller.impl;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.SalesRepDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.interfaces.ISalesRepEdge;
import com.ironhackemofcrmmicroservices.edgecontactservice.service.interfaces.ISalesRepEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SalesRepEdgeController implements ISalesRepEdge {

    @Autowired
    ISalesRepEdgeService salesRepService;

    @PostMapping("/store-salesrep")
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRepDto storeSalesRep(@RequestBody @Valid SalesRepDto salesRepDTO) {
        return salesRepService.storeSalesRep(salesRepDTO);
    }

    @GetMapping("/get-salesrep/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesRepDto getSalesRep(@PathVariable Integer id) {
        return salesRepService.getSalesRep(id);
    }

    @GetMapping("/get-salesrep")
    @ResponseStatus(HttpStatus.OK)
    public List<SalesRepDto> getAllSalesRep() {
        return salesRepService.getAllSalesRep();
    }

    @PutMapping("/salesrep-lead/{salesRepId}/{leadId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associateLeadToSalesRep(@PathVariable Integer salesRepId,
                                        @PathVariable Integer leadId) {
        salesRepService.associateLeadToSalesRep(salesRepId, leadId);
    }

    @PutMapping("/salesrep-opp/{salesRepId}/{oppId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associateOppToSalesRep(@PathVariable Integer salesRepId,
                                       @PathVariable Integer oppId) {
        salesRepService.associateOppToSalesRep(salesRepId, oppId);
    }
}
