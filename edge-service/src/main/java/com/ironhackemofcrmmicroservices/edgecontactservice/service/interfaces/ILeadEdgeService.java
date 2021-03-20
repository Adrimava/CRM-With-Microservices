package com.ironhackemofcrmmicroservices.edgecontactservice.service.interfaces;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.LeadDto;

import java.util.List;

public interface ILeadEdgeService {
    LeadDto getLead(Integer id);

    List<LeadDto> getAllLeads();

    LeadDto storeLead(LeadDto leadDto);

    public void deleteLead(Integer id);
}
