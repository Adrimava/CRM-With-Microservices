package com.ironhack.emofcrmmicroservices.salesRepservice.repository;

import com.ironhack.emofcrmmicroservices.salesRepservice.model.OpportunityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<OpportunityId, Integer> {
    @Query("SELECT CAST(s.id AS int), CAST(COUNT(*) AS int) FROM OpportunityId o JOIN SalesRep s ON o.salesRep = s.id GROUP BY o.salesRep")
    List<Object[]> getOppCountBySalesRep();
}
