package com.ironhack.emofcrmmicroservices.leadservice.controller.interfaces;

import com.ironhack.emofcrmmicroservices.leadservice.controller.dto.LeadDto;

import java.util.List;

public interface ILeadController {
    public LeadDto getLead(Integer id);

    public List<LeadDto> getAllLeads();

    public LeadDto storeLead(LeadDto leadDto);

    public void deleteLead(Integer id);


}
