package com.ironhack.emofcrmmicroservices.contactservice.repository;
import com.ironhack.emofcrmmicroservices.contactservice.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
