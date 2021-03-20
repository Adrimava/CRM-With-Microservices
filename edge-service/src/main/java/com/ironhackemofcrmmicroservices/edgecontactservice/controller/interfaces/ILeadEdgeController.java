package com.ironhackemofcrmmicroservices.edgecontactservice.controller.interfaces;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.LeadDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ILeadEdgeController {
    public LeadDto getLead(Integer id);

    public List<LeadDto> getAllLeads();

    public LeadDto storeLead(LeadDto leadDto);

    public void deleteLead(Integer id);


}
