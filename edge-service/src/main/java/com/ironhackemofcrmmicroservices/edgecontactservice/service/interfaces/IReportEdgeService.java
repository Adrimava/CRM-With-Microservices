package com.ironhackemofcrmmicroservices.edgecontactservice.service.interfaces;

import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.*;

import java.util.List;

public interface IReportEdgeService {
    List<LeadsBySalesRepDto> getLeadsBySalesRep();

    List<OppsBySalesRepDto> getOppsBySalesRep();

    List<OppsBySalesRepDto> getOppsBySalesRepAndStatus(String status);

    List<OppsByProductDto> getOppsByProduct();

    List<OppsByProductDto> getOppsByProductAndStatus(String status);

    List<OppsByIndustryDto> getOppsByIndustry();

    List<OppsByIndustryDto> getOppsByIndustryAndStatus(String status);

    List<OppsByCityDto> getOppCountByCity();

    List<OppsByCityDto> getOppsByCityAndStatus(String status);

	List<OppsByCountryDto> getOppsByCountry();

    List<OppsByCountryDto> getOppsByCountryAndStatus(String status);

    public Double getMeanOppsPerAccount();

    public Integer getMaxOppsPerAccount();

    public Integer getMinOppsPerAccount();

    public Double getMedianOppsPerAccount();

    public Double getMeanEmployeeCount();

    public Integer getMaxEmployeeCount();

    public Integer getMinEmployeeCount();

    public Double getMedianEmployeeCount();

    Double getMeanQuantityOrderedProducts();

    Integer getMaxQuantityOrderedProducts();

    Integer getMinQuantityOrderedProducts();

    Double getMedianQuantityOrderedProducts();
}
