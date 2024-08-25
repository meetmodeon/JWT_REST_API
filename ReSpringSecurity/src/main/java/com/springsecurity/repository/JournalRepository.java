package com.springsecurity.repository;

import com.springsecurity.model.Journal;
import com.springsecurity.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalRepository extends JpaRepository<Journal,Long> {
    List<Journal> findByPerson(Person person);
    Journal findByTitle(String title);


}
