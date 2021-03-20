package com.ironhack.emofcrmmicroservices.accountmicroservice.repository;

import com.ironhack.emofcrmmicroservices.accountmicroservice.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.List;

@Repository
public interface OpportunityIdRepository extends JpaRepository<OpportunityId, Integer> {

    @Query("SELECT a.city, CAST(COUNT(*) AS int) FROM OpportunityId o JOIN Account a ON o.account = a.id GROUP BY a.city")
    List<Object[]> getOppCountByCity();
    @Query("SELECT CAST(a.id AS int), CAST(COUNT(*) AS int) FROM OpportunityId o JOIN Account a ON o.account = a.id GROUP BY o.account")
    List<Object[]> getOppCountByAccount();
}
