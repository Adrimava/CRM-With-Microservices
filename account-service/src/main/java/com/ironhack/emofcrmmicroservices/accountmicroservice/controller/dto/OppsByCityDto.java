package com.ironhack.emofcrmmicroservices.accountmicroservice.controller.dto;

public class OppsByCityDto {
    private String city;
    private Integer oppCount;

    public OppsByCityDto(String city, Integer oppCount) {
        this.city = city;
        this.oppCount = oppCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getOppCount() {
        return oppCount;
    }

    public void setOppCount(Integer oppCount) {
        this.oppCount = oppCount;
    }
}
