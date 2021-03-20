package com.ironhack.emofcrmmicroservices.accountmicroservice.controller.dto;

import javax.validation.constraints.*;
import java.util.*;

public class AccountDto {
    private Integer id;
    @NotEmpty
    private String industry;
    @NotNull
    private int employeeCount;
    @NotEmpty
    private String city;
    @NotEmpty
    private String country;
    @NotEmpty
    private List<Integer> contactList;
    @NotEmpty
    private List<Integer> opportunityList;

    public AccountDto() {
    }

    public AccountDto(Integer id, @NotEmpty String industry, @NotNull int employeeCount, @NotEmpty String city,
                      @NotEmpty String country, @NotEmpty List<Integer> contactList, @NotEmpty List<Integer> opportunityList) {
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
