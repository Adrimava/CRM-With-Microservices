package com.ironhackemofcrmmicroservices.edgecontactservice.client;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("opportunity-service")
public interface OpportunityClient {
    @GetMapping("/get-opportunity/{id}")
    OpportunityDto getOpportunity(@PathVariable Integer id);

    @PostMapping("/convert-opportunity")
    void convertOpportunity(@RequestBody ConvertOpportunityDto convertOpportunityDto);

    @PutMapping("/close-opportunity")
    void closeOpportunity(@RequestBody CloseOpportunityDto closeOpportunityDto);

    @PutMapping("/report/opps-by-salesRep/{status}")
    List<OppsBySalesRepDto> getOppsBySalesRepAndStatus(@RequestBody List<SalesRepDto> salesRepDtoList, @PathVariable String status);

    @GetMapping("/report/opps-by-product")
    List<OppsByProductDto> getOppsByProduct();

    @GetMapping("/report/opps-by-product/{status}")
    List<OppsByProductDto> getOppsByProductAndStatus(@PathVariable String status);

    @PutMapping("/report/opps-by-industry/{status}")
    @ResponseStatus(HttpStatus.OK)
    List<OppsByIndustryDto> getOppsByIndustryAndStatus(@PathVariable String status, @RequestBody List<AccountDto> accounts);

    @PostMapping("report/opps-by-city/{status}")
    public List<OppsByCityDto> getOppsByCityAndStatus(@RequestBody List<AccountDto> accountDtoList, @PathVariable String status);



    @PutMapping("/report/opps-by-country/{status}")
    public List<OppsByCountryDto> getOppsByCountryAndStatus(@RequestBody List<AccountDto> accountDtoList, @PathVariable String status);

    @GetMapping("/report/quantity-ordered-products/mean")
    Double getMeanQuantityOrderedProducts();

    @GetMapping("/report/quantity-ordered-products/max")
    Integer getMaxQuantityOrderedProducts();

    @GetMapping("/report/quantity-ordered-products/min")
    Integer getMinQuantityOrderedProducts();

    @GetMapping("/report/quantity-ordered-products/median")
    Double getMedianQuantityOrderedProducts();
}
