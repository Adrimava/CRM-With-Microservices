package com.ironhack.emofcrmmicroservices.salesRepservice.service.impl;

import com.ironhack.emofcrmmicroservices.salesRepservice.controller.dto.LeadsBySalesRepDto;
import com.ironhack.emofcrmmicroservices.salesRepservice.controller.dto.OppsBySalesRepDto;
import com.ironhack.emofcrmmicroservices.salesRepservice.controller.dto.SalesRepDto;
import com.ironhack.emofcrmmicroservices.salesRepservice.model.LeadId;
import com.ironhack.emofcrmmicroservices.salesRepservice.model.OpportunityId;
import com.ironhack.emofcrmmicroservices.salesRepservice.model.SalesRep;
import com.ironhack.emofcrmmicroservices.salesRepservice.repository.LeadRepository;
import com.ironhack.emofcrmmicroservices.salesRepservice.repository.OpportunityRepository;
import com.ironhack.emofcrmmicroservices.salesRepservice.repository.SalesRepRepository;
import com.ironhack.emofcrmmicroservices.salesRepservice.service.interfaces.ISalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesRepService implements ISalesRepService {

    @Autowired
    SalesRepRepository salesRepRepository;

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    OpportunityRepository opportunityRepository;

    public SalesRepDto storeSalesRep(SalesRepDto salesRepDTO) {
        SalesRep salesRep = new SalesRep(salesRepDTO.getName());

        salesRepRepository.save(salesRep);
        return salesRepDTO;
    }

    public SalesRepDto getSalesRep(Integer id) {
        Optional<SalesRep> salesRep = salesRepRepository.findById(id);

        if (salesRep.isPresent()) {
            List<Integer> leads = new ArrayList<>();
            List<Integer> opps = new ArrayList<>();
            for (LeadId leadId : salesRep.get().getLeads()) {
                leads.add(leadId.getLeadId());
            }
            for (OpportunityId opportunityId : salesRep.get().getOpportunities()) {
                opps.add(opportunityId.getOppId());
            }
            return new SalesRepDto(salesRep.get().getId(), salesRep.get().getName(), leads, opps);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<SalesRepDto> getAllSalesRep() {
        List<SalesRep> salesRepList = salesRepRepository.findAll();
        List<SalesRepDto> salesRepDTOList = new ArrayList<>();

        for (SalesRep salesRep : salesRepList) {
            List<Integer> leads = new ArrayList<>();
            List<Integer> opps = new ArrayList<>();
            for (LeadId leadId : salesRep.getLeads()) {
                leads.add(leadId.getLeadId());
            }
            for (OpportunityId opportunityId : salesRep.getOpportunities()) {
                opps.add(opportunityId.getOppId());
            }
            salesRepDTOList.add(new SalesRepDto(salesRep.getId(), salesRep.getName(), leads, opps));
        }
        return salesRepDTOList;
    }

    public void associateLeadToSalesRep(Integer salesRepId, Integer leadId) {
        Optional<SalesRep> salesRep = salesRepRepository.findById(salesRepId);

        if (salesRep.isPresent()) {
            LeadId lead = new LeadId(leadId, salesRep.get());
            leadRepository.save(lead);
            List<LeadId> leads = salesRep.get().getLeads();
            leads.add(lead);
            SalesRep newSalesRep = new SalesRep(salesRep.get().getName());
            newSalesRep.setId(salesRepId);
            newSalesRep.setLeads(new ArrayList<>(leads));
            newSalesRep.setOpportunities(new ArrayList<>(salesRep.get().getOpportunities()));
            salesRepRepository.save(newSalesRep);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales Rep not found");
        }
    }

    public void associateOppToSalesRep(Integer salesRepId, Integer oppId) {
        Optional<SalesRep> salesRep = salesRepRepository.findById(salesRepId);

        if (salesRep.isPresent()) {
            OpportunityId opportunity = new OpportunityId(oppId, salesRep.get());
            opportunityRepository.save(opportunity);
            List<OpportunityId> opportunities = salesRep.get().getOpportunities();
            opportunities.add(opportunity);
            salesRep.get().setId(salesRepId);
            salesRep.get().setOpportunities(opportunities);
            salesRepRepository.save(salesRep.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales Rep not found");
        }
    }

    public List<LeadsBySalesRepDto> getLeadsBySalesRep() {
        List<Object[]> repoList = leadRepository.getLeadCountBySalesRep();
        List<LeadsBySalesRepDto> dtoList = new ArrayList<>();
        for(Object[] element: repoList) {
            dtoList.add(new LeadsBySalesRepDto((Integer) element[0], (Integer) element[1]));
        }
        return dtoList;
    }

    public List<OppsBySalesRepDto> getOppsBySalesRep() {
        List<Object[]> repoList = opportunityRepository.getOppCountBySalesRep();
        List<OppsBySalesRepDto> dtoList = new ArrayList<>();
        for(Object[] element: repoList) {
            dtoList.add(new OppsBySalesRepDto((Integer) element[0], (Integer) element[1]));
        }
        return dtoList;
    }

}
