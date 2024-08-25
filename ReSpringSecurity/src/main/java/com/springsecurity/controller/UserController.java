package com.springsecurity.controller;

import com.springsecurity.doa.PersonDao;
import com.springsecurity.model.Person;
import com.springsecurity.repository.PersonRepository;
import com.springsecurity.service.PersonServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PersonServiceImpl personServiceImpl;
    @Autowired
    private PersonRepository personRepository;


    @PutMapping
    public ResponseEntity<?> updatePerson(@RequestBody PersonDao personDao){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        System.out.println(userName);
        Person person=personServiceImpl.upDatePerson(userName,personDao);
        return ResponseEntity.status(HttpStatus.OK).body(person.getName()+" is Updated successfully!!");
    }
    @DeleteMapping
    public ResponseEntity<?> deleteByName(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        int row=personRepository.deleteByName(userName);
        return ResponseEntity.status(HttpStatus.OK).body("("+row+")Row is deleted Successfully!!");
    }
    @GetMapping
    public ResponseEntity<Person> getAUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        Person person=personServiceImpl.getPersonDetails(userName);
        return ResponseEntity.ok(person);
    }

}
