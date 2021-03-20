package com.ironhack.emofcrmmicroservices.salesRepservice.controller.dto;

public class OppsBySalesRepDto {
    private Integer salesRepId;
    private Integer oppCount;

    public OppsBySalesRepDto(Integer salesRepId, Integer oppCount) {
        this.salesRepId = salesRepId;
        this.oppCount = oppCount;
    }

    public Integer getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Integer salesRepId) {
        this.salesRepId = salesRepId;
    }

    public Integer getOppCount() {
        return oppCount;
    }

    public void setOppCount(Integer oppCount) {
        this.oppCount = oppCount;
    }
}
