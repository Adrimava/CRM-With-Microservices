package com.ironhack.emofcrmmicroservices.salesRepservice.repository;

import com.ironhack.emofcrmmicroservices.salesRepservice.model.LeadId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<LeadId, Integer> {
    @Query("SELECT CAST(s.id AS int), CAST(COUNT(*) AS int) FROM LeadId l JOIN SalesRep s ON l.salesRep = s.id GROUP BY l.salesRep")
    List<Object[]> getLeadCountBySalesRep();

}
