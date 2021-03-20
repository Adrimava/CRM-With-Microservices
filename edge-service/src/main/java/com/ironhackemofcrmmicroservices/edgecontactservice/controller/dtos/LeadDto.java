package com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LeadDto {

    /**
     * -------------- Properties --------------
     **/

    private Integer id;
    @NotEmpty
    @Pattern(regexp = "(.+) (.+)", message = "Name must have first name and last name")
    private String name;
    @NotEmpty
    @Pattern(regexp = "^[679][0-9]{8}$", message = "Not valid phone number format")
    private String phoneNumber;
    @NotEmpty
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Invalid email")
    private String email;
    @NotEmpty
    private String companyName;
    @NotNull
    private Integer salesRepId;


    /**
     * -------------- Constructor --------------
     **/

    public LeadDto() {
    }

    /**
     * ----------- Getters & Setters -----------
     **/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Integer salesRepId) {
        this.salesRepId = salesRepId;
    }
}
