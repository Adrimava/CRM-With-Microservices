package com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto;

import javax.validation.constraints.NotNull;

public class UpdateAccountDto {
    @NotNull
    private Integer accountId;
    @NotNull
    private Integer contactId;
    @NotNull
    private Integer opportunityId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Integer opportunityId) {
        this.opportunityId = opportunityId;
    }
}
