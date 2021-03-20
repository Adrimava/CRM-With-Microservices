package com.ironhack.emofcrmmicroservices.accountmicroservice.model;

import javax.persistence.*;

@Entity
@Table(name="opportunity_account_id")
public class OpportunityId {
    @Id
    private Integer opportunityId;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public OpportunityId() {
    }

    public OpportunityId(Integer opportunityId, Account account) {
        this.opportunityId = opportunityId;
        this.account = account;
    }

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Integer opportunityId) {
        this.opportunityId = opportunityId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
