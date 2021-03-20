package com.ironhackemofcrmmicroservices.edgecontactservice.controller.interfaces;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.ContactDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.LeadDto;

public interface IContactEdgeController {

    public ContactDto storeContact(LeadDto leadDto);
}
