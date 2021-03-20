package com.ironhack.emofcrmmicroservices.salesRepservice.controller.interfaces;

import com.ironhack.emofcrmmicroservices.salesRepservice.controller.dto.LeadsBySalesRepDto;
import com.ironhack.emofcrmmicroservices.salesRepservice.controller.dto.OppsBySalesRepDto;
import com.ironhack.emofcrmmicroservices.salesRepservice.controller.dto.SalesRepDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ISalesRepController {
    SalesRepDto storeSalesRep(SalesRepDto salesRepDTO);

    SalesRepDto getSalesRep(Integer id);

    List<SalesRepDto> getAllSalesRep();

    void associateLeadToSalesRep(Integer salesRepId, Integer leadId);

    void associateOppToSalesRep(Integer salesRepId, Integer oppId);

    List<LeadsBySalesRepDto> getLeadsBySalesRep();

    List<OppsBySalesRepDto> getOppsBySalesRep();


}
