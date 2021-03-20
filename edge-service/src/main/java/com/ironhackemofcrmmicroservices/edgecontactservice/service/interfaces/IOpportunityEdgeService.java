package com.ironhackemofcrmmicroservices.edgecontactservice.service.interfaces;


import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.CloseOpportunityDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.ConvertOpportunityDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.OpportunityDto;

public interface IOpportunityEdgeService {
    void convertOpportunity(ConvertOpportunityDto convertOpportunityDto);

    void closeOpportunity(CloseOpportunityDto closeOpportunityDto);

    OpportunityDto getOpportunity(Integer id);


}
