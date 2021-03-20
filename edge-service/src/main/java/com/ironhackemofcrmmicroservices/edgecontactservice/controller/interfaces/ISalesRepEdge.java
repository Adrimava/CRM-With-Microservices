package com.ironhackemofcrmmicroservices.edgecontactservice.controller.interfaces;


import com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos.SalesRepDto;

import java.util.List;

public interface ISalesRepEdge {
    SalesRepDto storeSalesRep(SalesRepDto salesRepDTO);

    SalesRepDto getSalesRep(Integer id);

    List<SalesRepDto> getAllSalesRep();

    void associateLeadToSalesRep(Integer salesRepId, Integer leadId);

    void associateOppToSalesRep(Integer salesRepId, Integer oppId);
}
