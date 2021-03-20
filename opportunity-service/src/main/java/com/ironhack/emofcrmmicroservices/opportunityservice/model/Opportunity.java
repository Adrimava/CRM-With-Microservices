package com.ironhack.emofcrmmicroservices.opportunityservice.model;

import com.ironhack.emofcrmmicroservices.opportunityservice.enums.Product;
import com.ironhack.emofcrmmicroservices.opportunityservice.enums.Status;

import javax.persistence.*;

@Entity
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Product product;
    private Integer quantity;
    private Integer contactId;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer salesRepId;

    public Opportunity(Product product, Integer quantity, Integer contactId, Status status, Integer salesRepId) {
        this.product = product;
        this.quantity = quantity;
        this.contactId = contactId;
        this.status = status;
        this.salesRepId = salesRepId;
    }

    public Opportunity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Integer salesRepId) {
        this.salesRepId = salesRepId;
    }
}
