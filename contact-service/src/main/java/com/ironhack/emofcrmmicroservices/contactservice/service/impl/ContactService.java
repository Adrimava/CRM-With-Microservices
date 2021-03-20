package com.ironhack.emofcrmmicroservices.contactservice.service.impl;
import com.ironhack.emofcrmmicroservices.contactservice.dtos.ContactDto;
import com.ironhack.emofcrmmicroservices.contactservice.dtos.LeadDto;
import com.ironhack.emofcrmmicroservices.contactservice.model.Contact;
import com.ironhack.emofcrmmicroservices.contactservice.repository.ContactRepository;
import com.ironhack.emofcrmmicroservices.contactservice.service.interfaces.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService implements IContactService {

    @Autowired
    private ContactRepository contactRepository;

    /** Method to create and store a new Contact in our database **/
    public ContactDto storeContact(LeadDto leadDto) {

        // Create contact through leadDto information
        Contact contact = new Contact(
                leadDto.getName(),
                leadDto.getPhoneNumber(),
                leadDto.getEmail(),
                leadDto.getCompanyName()
        );

        // Save the contact in the repository
        contactRepository.save(contact);

        // Create a contactDto
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setName(leadDto.getName());
        contactDto.setPhoneNumber(leadDto.getPhoneNumber());
        contactDto.setEmail(leadDto.getEmail());
        contactDto.setCompanyName(leadDto.getCompanyName());

        // Return the contact dto
        return contactDto;
    }
}
