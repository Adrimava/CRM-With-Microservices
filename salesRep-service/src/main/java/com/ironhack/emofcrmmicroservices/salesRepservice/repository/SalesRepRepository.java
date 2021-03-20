package com.ironhack.emofcrmmicroservices.salesRepservice.repository;

import com.ironhack.emofcrmmicroservices.salesRepservice.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {


}
