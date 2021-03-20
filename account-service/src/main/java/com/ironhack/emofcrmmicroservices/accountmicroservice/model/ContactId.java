package com.ironhack.emofcrmmicroservices.accountmicroservice.model;

import javax.persistence.*;

@Entity
public class ContactId {
    @Id
    private Integer contactId;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public ContactId() {
    }

    public ContactId(Integer contactId, Account account) {
        this.contactId = contactId;
        this.account = account;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
