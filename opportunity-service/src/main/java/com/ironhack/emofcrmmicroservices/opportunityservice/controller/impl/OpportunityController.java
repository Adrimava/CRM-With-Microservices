package com.ironhack.emofcrmmicroservices.opportunityservice.controller.impl;

import com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto.*;
import com.ironhack.emofcrmmicroservices.opportunityservice.controller.interfaces.IOpportunityController;
import com.ironhack.emofcrmmicroservices.opportunityservice.service.interfaces.IOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OpportunityController implements IOpportunityController {

    @Autowired
    private IOpportunityService opportunityService;

    @GetMapping("/get-opportunity/{id}")
    public OpportunityDto getOpportunity(@PathVariable Integer id) {
        return opportunityService.getOpportunity(id);
    }

    @PostMapping("/convert-opportunity")
    @ResponseStatus(HttpStatus.CREATED)
    public void convertOpportunity(@RequestBody @Valid ConvertOpportunityDto convertOpportunityDto) {
        opportunityService.convertOpportunity(convertOpportunityDto);
    }

    @PutMapping("/close-opportunity")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closeOpportunity(@RequestBody @Valid CloseOpportunityDto closeOpportunityDto) {
        opportunityService.closeOpportunity(closeOpportunityDto);
    }

    @PutMapping("/report/opps-by-salesRep/{status}")
    public List<OppsBySalesRepDto> getOppsBySalesRepAndStatus(@RequestBody List<SalesRepDto> salesRepDtoList, @PathVariable String status) {
        return opportunityService.getOppsBySalesRepAndStatus(salesRepDtoList, status);
    }

    @GetMapping("/report/opps-by-product")
    public List<OppsByProductDto> getOppsByProduct() {
        return opportunityService.getOppsByProduct();
    }

    @GetMapping("/report/opps-by-product/{status}")
    public List<OppsByProductDto> getOppsByProductAndStatus(@PathVariable String status) {
        return opportunityService.getOppsByProductAndStatus(status);
    }

    @PutMapping("/report/opps-by-industry/{status}")
    public List<OppsByIndustryDto> getOppsByIndustry(@PathVariable String status, @RequestBody List<AccountDto> accounts){
        return opportunityService.getOppsByIndustryAndStatus(status, accounts);
    }

    @PostMapping("report/opps-by-city/{status}")
    public List<OppsByCityDto> getOppsByCityAndStatus(@RequestBody List<AccountDto> accountDtoList, @PathVariable String status) {
        return opportunityService.getOppsByCityAndStatus(accountDtoList, status);
    }

    @PutMapping("/report/opps-by-country/{status}")
    public List<OppsByCountryDto> getOppsByCountryAndStatus(@RequestBody List<AccountDto> accountDtoList, @PathVariable String status) {
        return opportunityService.getOppsByCountryAndStatus(accountDtoList, status);
    }

    @GetMapping("/report/quantity-ordered-products/mean")
    public Double getMeanQuantityOrderedProducts() {
        return opportunityService.getMeanQuantityOrderedProducts();
    }

    @GetMapping("/report/quantity-ordered-products/max")
    public Integer getMaxQuantityOrderedProducts() {
        return opportunityService.getMaxQuantityOrderedProducts();
    }

    @GetMapping("/report/quantity-ordered-products/min")
    public Integer getMinQuantityOrderedProducts() {
        return opportunityService.getMinQuantityOrderedProducts();
    }

    @GetMapping("/report/quantity-ordered-products/median")
    public Double getMedianQuantityOrderedProducts() {
        return opportunityService.getMedianQuantityOrderedProducts();
    }


}
