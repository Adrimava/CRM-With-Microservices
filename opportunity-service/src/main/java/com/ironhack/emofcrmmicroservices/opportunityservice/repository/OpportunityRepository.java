package com.ironhack.emofcrmmicroservices.opportunityservice.repository;

import com.ironhack.emofcrmmicroservices.opportunityservice.model.Opportunity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    @Query("SELECT AVG(quantity) FROM Opportunity")
    public Double findAvgProductsOrdered();

    @Query("SELECT MAX(quantity) FROM Opportunity")
    public Integer findMaxProductsOrdered();

    @Query("SELECT MIN(quantity) FROM Opportunity")
    public Integer findMinProductsOrdered();

    @Query("SELECT quantity FROM Opportunity")
    public List<Integer> findProductsOrdered();
}
