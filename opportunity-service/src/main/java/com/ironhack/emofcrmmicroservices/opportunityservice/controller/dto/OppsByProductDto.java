package com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto;

public class OppsByProductDto {
    private String product;
    private Integer oppCount;

    public OppsByProductDto(String product, Integer oppCount) {
        this.product = product;
        this.oppCount = oppCount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getOppCount() {
        return oppCount;
    }

    public void setOppCount(Integer oppCount) {
        this.oppCount = oppCount;
    }
}
