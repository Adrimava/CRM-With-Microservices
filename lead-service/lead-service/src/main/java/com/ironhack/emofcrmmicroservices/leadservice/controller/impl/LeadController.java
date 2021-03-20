package com.ironhack.emofcrmmicroservices.leadservice.controller.impl;

import com.ironhack.emofcrmmicroservices.leadservice.controller.dto.LeadDto;
import com.ironhack.emofcrmmicroservices.leadservice.controller.interfaces.ILeadController;
import com.ironhack.emofcrmmicroservices.leadservice.service.interfaces.ILeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LeadController implements ILeadController {

    @Autowired
    private ILeadService leadService;

    @GetMapping("/get-lead/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeadDto getLead(@PathVariable Integer id) {
        return leadService.getLead(id);
    }

    @GetMapping("/get-leads")
    @ResponseStatus(HttpStatus.OK)
    public List<LeadDto> getAllLeads() {
        return leadService.getAllLeads();
    }

    @PostMapping("/store-lead")
    @ResponseStatus(HttpStatus.CREATED)
    public LeadDto storeLead(@Valid @RequestBody LeadDto leadDto) {
        return leadService.storeLead(leadDto);
    }


    @DeleteMapping("/delete-lead/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLead(@PathVariable Integer id) {
        leadService.deleteLead(id);
    }

}
