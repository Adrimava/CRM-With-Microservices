package com.ironhack.emofcrmmicroservices.leadservice.service.impl;

import com.ironhack.emofcrmmicroservices.leadservice.client.SalesRepClient;
import com.ironhack.emofcrmmicroservices.leadservice.controller.dto.LeadDto;
import com.ironhack.emofcrmmicroservices.leadservice.controller.dto.SalesRepDTO;
import com.ironhack.emofcrmmicroservices.leadservice.model.Lead;
import com.ironhack.emofcrmmicroservices.leadservice.repository.LeadRepository;
import com.ironhack.emofcrmmicroservices.leadservice.service.interfaces.ILeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadService implements ILeadService {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private SalesRepClient salesRepClient;


    public LeadDto getLead(Integer id) {
        Lead lead = leadRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lead for that id doesn't exists"));
        return new LeadDto(lead.getId(), lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName(), lead.getSalesRepId());
    }

    public List<LeadDto> getAllLeads() {
        List<Lead> leadList = leadRepository.findAll();
        List<LeadDto> leadDtoList = new ArrayList<LeadDto>();
        for(Lead lead: leadList){
            LeadDto leadDto = new LeadDto(lead.getId(), lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName(), lead.getSalesRepId());
            leadDtoList.add(leadDto);
        }
        return leadDtoList;
    }

    public void deleteLead(Integer id) {
        leadRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lead for that id doesn't exists"));
        leadRepository.deleteById(id);
    }

    public LeadDto storeLead(LeadDto leadDto) {
        try {
            SalesRepDTO salesRepDto = salesRepClient.getSalesrep(leadDto.getSalesRepId());
            if(salesRepDto == null)  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid sales rep id");
            Lead lead = leadRepository.save(new Lead(leadDto.getName(), leadDto.getPhoneNumber(), leadDto.getEmail(), leadDto.getCompanyName(), leadDto.getSalesRepId()));
            leadDto.setId(lead.getId());
            salesRepClient.associateLeadToSalesRep(leadDto.getSalesRepId(), lead.getId());
            return leadDto;
        } catch (ResponseStatusException exception) {
            if(leadDto.getId() != null) leadRepository.deleteById(leadDto.getId());
            if(exception.getStatus().is4xxClientError()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not valid sales rep id");
            else throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "SalesRep service not available");
        }
    }

}
