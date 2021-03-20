package com.ironhack.emofcrmmicroservices.salesRepservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class SalesRep {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@OneToMany(mappedBy = "salesRep")
	private List<LeadId> leads;
	@OneToMany(mappedBy = "salesRep")
	private List<OpportunityId> opportunities;

	public SalesRep() {
	}

	public SalesRep(String name) {
		this.name = name;
	}

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

	public List<LeadId> getLeads() {
		return leads;
	}

	public void setLeads(List<LeadId> leads) {
		this.leads = leads;
	}

	public List<OpportunityId> getOpportunities() {
		return opportunities;
	}

	public void setOpportunities(List<OpportunityId> opportunities) {
		this.opportunities = opportunities;
	}
}
