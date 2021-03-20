package com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos;

import java.util.List;

public class AccountDto {
    private Integer id;
    private String industry;
    private int employeeCount;
    private String city;
    private String country;
    private List<Integer> contactList;
    private List<Integer> opportunityList;

    public AccountDto() {
    }

    public AccountDto(Integer id, String industry, int employeeCount, String city, String country, List<Integer> contactList, List<Integer> opportunityList) {
        this.id = id;
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
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
