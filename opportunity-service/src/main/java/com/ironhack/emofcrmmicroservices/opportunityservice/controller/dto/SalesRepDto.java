package com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class SalesRepDto {
    private Integer id;
    @NotEmpty
    private String name;
    private List<Integer> leads;
    private List<Integer> opportunities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getLeads() {
        return leads;
    }

    public void setLeads(List<Integer> leads) {
        this.leads = leads;
    }

    public List<Integer> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<Integer> opportunities) {
        this.opportunities = opportunities;
    }
}
