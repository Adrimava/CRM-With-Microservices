package com.ironhack.emofcrmmicroservices.contactservice.service.interfaces;
import com.ironhack.emofcrmmicroservices.contactservice.dtos.ContactDto;
import com.ironhack.emofcrmmicroservices.contactservice.dtos.LeadDto;

public interface IContactService {

    /** Method to create and store a new Contact in our database **/
    public ContactDto storeContact(LeadDto leadDto);
}
