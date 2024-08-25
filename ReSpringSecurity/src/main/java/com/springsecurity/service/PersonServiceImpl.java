package com.springsecurity.service;

import com.springsecurity.doa.PersonDao;
import com.springsecurity.model.Person;
import com.springsecurity.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void createPerson(PersonDao personDao) {
        Person person=new Person();
        person.setName(personDao.getName());
        person.setEmail(personDao.getEmail());
        person.setPassword(passwordEncoder.encode(personDao.getPassword()));
        person.setRole(personDao.getRole());
        personRepository.save(person);
    }

    @Override
    public Person getPersonDetails(String userName) {
       Optional<Person> person=personRepository.findByName(userName);
       if(person.isPresent()){
           Person oldPerson=person.get();
           return oldPerson;
       }else {
           throw new RuntimeException("User Not found for this id:"+userName);
       }

    }

    @Override
    public List<Person> getAllPersonDetails() {
        return personRepository.findAll();
    }

    @Override
    public Person upDatePerson(String userName,PersonDao personDao) {
       Optional<Person> person=personRepository.findByName(userName);
       if(person.isPresent()){
           Person existPerson=person.get();
          if(personDao.getName()!=null){
              existPerson.setName(personDao.getName());
          }if(personDao.getEmail()!=null){
               existPerson.setEmail(personDao.getEmail());
           }
           if(personDao.getPassword()!=null){
               existPerson.setPassword(passwordEncoder.encode(personDao.getPassword()));
           }
           if(personDao.getRole()!=null){
               existPerson.setRole(personDao.getRole());
           }
           return personRepository.save(existPerson);
       }else {
           throw new RuntimeException("User not found with name: " +personDao.getName());
       }
    }

    @Override
    public void DeletePerson(Long id) {
        if (personRepository.existsById(id)){
            personRepository.deleteById(id);
        }
    }
}
