package com.ironhack.emofcrmmicroservices.contactservice.controller.impl;
import com.ironhack.emofcrmmicroservices.contactservice.controller.interfaces.IContactController;
import com.ironhack.emofcrmmicroservices.contactservice.dtos.ContactDto;
import com.ironhack.emofcrmmicroservices.contactservice.dtos.LeadDto;
import com.ironhack.emofcrmmicroservices.contactservice.service.interfaces.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class ContactController implements IContactController {

    @Autowired
    private IContactService contactService;

    @PostMapping("/store-contact")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDto storeContact(@RequestBody @Valid LeadDto leadDto) {
        return contactService.storeContact(leadDto);
    }
}
