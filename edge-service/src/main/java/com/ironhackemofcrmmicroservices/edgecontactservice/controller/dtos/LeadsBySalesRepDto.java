package com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos;

public class LeadsBySalesRepDto {
    private Integer salesRepId;
    private Integer countLead;

    public Integer getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Integer salesRepId) {
        this.salesRepId = salesRepId;
    }

    public Integer getCountLead() {
        return countLead;
    }

    public void setCountLead(Integer countLead) {
        this.countLead = countLead;
    }
}
