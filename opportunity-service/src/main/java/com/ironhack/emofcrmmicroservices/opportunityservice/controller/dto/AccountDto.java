package com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AccountDto {
    private Integer id;
    @NotEmpty
    private String industry;
    @NotNull
    private Integer employeeCount;
    @NotEmpty
    private String city;
    @NotEmpty
    private String country;
    @NotEmpty
    private List<Integer> contactList;
    @NotEmpty
    private List<Integer> opportunityList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
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

    public List<Integer> getContactList() {
        return contactList;
    }

    public void setContactList(List<Integer> contactList) {
        this.contactList = contactList;
    }

    public List<Integer> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(List<Integer> opportunityList) {
        this.opportunityList = opportunityList;
    }
}
