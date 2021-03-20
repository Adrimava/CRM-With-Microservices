package com.ironhack.emofcrmmicroservices.accountmicroservice.model;

import com.ironhack.emofcrmmicroservices.accountmicroservice.enums.*;

import javax.persistence.*;
import java.util.*;


@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<ContactId> contactList;
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<OpportunityId> opportunityList;


    public Account() {
    }

    public Account(Industry industry, int employeeCount, String city, String country, List<ContactId> contactList, List<OpportunityId> opportunityList) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportunityList = opportunityList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<ContactId> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactId> contactList) {
        this.contactList = contactList;
    }

    public List<OpportunityId> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(List<OpportunityId> opportunityList) {
        this.opportunityList = opportunityList;
    }
}
