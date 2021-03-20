package com.ironhack.emofcrmmicroservices.leadservice.repository;

import com.ironhack.emofcrmmicroservices.leadservice.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {

}
