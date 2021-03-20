package com.ironhack.emofcrmmicroservices.contactservice.dtos;
import javax.validation.constraints.NotEmpty;

public class ContactDto {

    /** -------------- Properties -------------- **/

    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String email;
    @NotEmpty
    private String companyName;

    /** -------------- Constructor -------------- **/

    public ContactDto() {
    }

    /** ----------- Getters & Setters ----------- **/

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
}
