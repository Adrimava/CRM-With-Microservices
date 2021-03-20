package com.ironhack.emofcrmmicroservices.salesRepservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name ="opportunity_sales_rep_id")
public class OpportunityId {
	@Id
	private Integer oppId;
	@ManyToOne
	@JoinColumn(name = "sales_rep_id")
	@JsonIgnore
	private SalesRep salesRep;

	public OpportunityId() {
	}

	public OpportunityId(Integer oppId, SalesRep salesRep) {
		this.oppId = oppId;
		this.salesRep = salesRep;
	}

	public Integer getOppId() {
		return oppId;
	}

	public void setOppId(Integer oppId) {
		this.oppId = oppId;
	}

	public SalesRep getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(SalesRep salesRep) {
		this.salesRep = salesRep;
	}
}
