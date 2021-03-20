package com.ironhack.emofcrmmicroservices.accountmicroservice.repository;

import com.ironhack.emofcrmmicroservices.accountmicroservice.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a.country, CAST(COUNT(*) AS int) FROM OpportunityId o JOIN Account a ON o.account = a.id GROUP BY o.account")
    List<Object[]> getOppsByCountry();

    @Query("SELECT AVG(employeeCount) FROM Account")
    public Double findAvgEmployeeCount();

    @Query("SELECT MAX(employeeCount) FROM Account")
    public Integer findMaxEmployeeCount();

    @Query("SELECT MIN(employeeCount) FROM Account")
    public Integer findMinEmployeeCount();

    @Query(value = "SELECT employeeCount FROM Account")
    public List<Integer> medianEmployeeCount();

}
