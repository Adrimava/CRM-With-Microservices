package com.ironhackemofcrmmicroservices.edgecontactservice.controller.interfaces;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IReportEdgeController {
    List<LeadsBySalesRepDto> getLeadsBySalesRep();

    List<OppsBySalesRepDto> getOppsBySalesRep();

    List<OppsBySalesRepDto> getOppsBySalesRepAndStatus(String status);

    List<OppsByProductDto> getOppsByProduct();

    List<OppsByProductDto> getOppsByProductAndStatus(String status);

    List<OppsByCityDto> getOppCountByCity();

    List<OppsByCityDto> getOppsByCityAndStatus(String status);

    List<OppsByCountryDto> getOppsByCountry();

    List<OppsByCountryDto> getOppsByCountryAndStatus(String status);

    Double getMeanOppsPerAccount();

    Integer getMaxOppsPerAccount();

    Integer getMinOppsPerAccount();

    Double getMedianOppsPerAccount();

    Double getMeanEmployeeCount();

    Integer getMaxEmployeeCount();

    Integer getMinEmployeeCount();

    Double getMedianEmployeeCount();

    Double getMeanQuantityOrderedProducts();

    Integer getMaxQuantityOrderedProducts();

    Integer getMinQuantityOrderedProducts();

    Double getMedianQuantityOrderedProducts();
}
