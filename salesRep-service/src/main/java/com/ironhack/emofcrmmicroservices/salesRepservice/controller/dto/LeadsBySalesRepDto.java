package com.ironhack.emofcrmmicroservices.salesRepservice.controller.dto;

public class LeadsBySalesRepDto {
    private Integer salesRepId;
    private Integer leadCount;

    public LeadsBySalesRepDto(Integer salesRepId, Integer leadCount) {
        this.salesRepId = salesRepId;
        this.leadCount = leadCount;
    }

    public Integer getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Integer salesRepId) {
        this.salesRepId = salesRepId;
    }

    public Integer getLeadCount() {
        return leadCount;
    }

    public void setLeadCount(Integer leadCount) {
        this.leadCount = leadCount;
    }
}
