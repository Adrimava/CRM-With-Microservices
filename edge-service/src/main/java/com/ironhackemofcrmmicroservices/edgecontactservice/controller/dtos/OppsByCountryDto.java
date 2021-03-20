package com.ironhackemofcrmmicroservices.edgecontactservice.controller.dtos;

public class OppsByCountryDto {
	private String country;
	private Integer opportunityId;

	public OppsByCountryDto(String country, Integer opportunityId) {
		this.country = country;
		this.opportunityId = opportunityId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(Integer opportunityId) {
		this.opportunityId = opportunityId;
	}
}
