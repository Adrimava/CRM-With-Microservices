package com.ironhackemofcrmmicroservices.edgecontactservice.service.interfaces;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.ContactDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.LeadDto;

public interface IContactEdgeService {

    public ContactDto storeContact(LeadDto leadDto);
}
