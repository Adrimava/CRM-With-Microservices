package com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos;

public class UpdateAccountDto {
    private Integer accountId;
    private Integer contactId;
    private Integer opportunityId;

    public UpdateAccountDto() {
    }

    public UpdateAccountDto(Integer accountId, Integer contactId, Integer opportunityId) {
        this.accountId = accountId;
        this.contactId = contactId;
        this.opportunityId = opportunityId;
    }

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
