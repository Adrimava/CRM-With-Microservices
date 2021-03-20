package com.ironhackemofcrmmicroservices.edgecontactservice.controller.interfaces;


import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.CloseOpportunityDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.ConvertOpportunityDto;

public interface IOpportunityEdgeController {
    void convertOpportunity(ConvertOpportunityDto convertOpportunityDto);

    void closeOpportunity(CloseOpportunityDto closeOpportunityDto);

}
