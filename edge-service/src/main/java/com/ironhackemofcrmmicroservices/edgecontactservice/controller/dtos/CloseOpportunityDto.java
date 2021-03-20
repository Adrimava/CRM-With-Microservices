package com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CloseOpportunityDto {
    @NotNull
    private Integer opportunityId;
    @NotEmpty
    private String status;

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Integer opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
