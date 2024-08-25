package com.springsecurity.service;

import com.springsecurity.doa.PersonDao;
import com.springsecurity.model.Person;

import java.util.List;
import java.util.Optional;


public interface PersonService {
    public void createPerson(PersonDao personDao);
    Person getPersonDetails(String userName);
     List<Person> getAllPersonDetails();

    Person upDatePerson(String userName, PersonDao personDao);

    public void DeletePerson(Long id);
}
