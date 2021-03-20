package com.ironhack.emofcrmmicroservices.salesRepservice.service.interfaces;

import com.ironhack.emofcrmmicroservices.salesRepservice.controller.dto.LeadsBySalesRepDto;
import com.ironhack.emofcrmmicroservices.salesRepservice.controller.dto.OppsBySalesRepDto;
import com.ironhack.emofcrmmicroservices.salesRepservice.controller.dto.SalesRepDto;

import java.util.List;

public interface ISalesRepService {
    SalesRepDto storeSalesRep(SalesRepDto salesRepDTO);

    SalesRepDto getSalesRep(Integer id);

    List<SalesRepDto> getAllSalesRep();

    void associateLeadToSalesRep(Integer salesRepId, Integer leadId);

    void associateOppToSalesRep(Integer salesRepId, Integer oppId);

    List<LeadsBySalesRepDto> getLeadsBySalesRep();

    List<OppsBySalesRepDto> getOppsBySalesRep();

}
