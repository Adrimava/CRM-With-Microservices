package com.ironhack.emofcrmmicroservices.salesRepservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class LeadId {
	@Id
	private Integer leadId;
	@ManyToOne
	@JoinColumn(name = "sales_rep_id")
	@JsonIgnore
	private SalesRep salesRep;

	public LeadId() {
	}

	public LeadId(Integer leadId, SalesRep salesRep) {
		this.leadId = leadId;
		this.salesRep = salesRep;
	}

	public Integer getLeadId() {
		return leadId;
	}

	public void setLeadId(Integer leadId) {
		this.leadId = leadId;
	}

	public SalesRep getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(SalesRep salesRep) {
		this.salesRep = salesRep;
	}
}
