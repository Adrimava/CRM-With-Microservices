package com.ironhackemofcrmmicroservices.edgecontactservice.controller.impl;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.ContactDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.LeadDto;
import com.ironhackemofcrmmicroservices.edgecontactservice.controller.interfaces.IContactEdgeController;
import com.ironhackemofcrmmicroservices.edgecontactservice.service.interfaces.IContactEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ContactEdgeController implements IContactEdgeController {

    @Autowired
    private IContactEdgeService edgeService;

    @PostMapping("/store-contact")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDto storeContact(@RequestBody @Valid LeadDto leadDto) {
        return edgeService.storeContact(leadDto);
    }


}
