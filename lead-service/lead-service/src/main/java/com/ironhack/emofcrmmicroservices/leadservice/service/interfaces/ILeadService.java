package com.ironhack.emofcrmmicroservices.leadservice.service.interfaces;

import com.ironhack.emofcrmmicroservices.leadservice.controller.dto.LeadDto;

import java.util.List;

public interface ILeadService {
    public LeadDto getLead(Integer id);
    public List<LeadDto> getAllLeads();
    public void deleteLead(Integer id);
    public LeadDto storeLead(LeadDto leadDto);
}
