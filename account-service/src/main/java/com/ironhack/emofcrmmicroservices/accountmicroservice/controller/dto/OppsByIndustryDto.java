package com.ironhack.emofcrmmicroservices.accountmicroservice.controller.dto;

public class OppsByIndustryDto {
    private String industry;
    private Integer oppCount;

    public OppsByIndustryDto(String industry, Integer oppCount) {
        this.industry = industry;
        this.oppCount = oppCount;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Integer getOppCount() {
        return oppCount;
    }

    public void setOppCount(Integer oppCount) {
        this.oppCount = oppCount;
    }
}
