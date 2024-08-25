package com.springsecurity.controller;

import com.springsecurity.model.Person;
import com.springsecurity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<Person>> getAll(){
        List<Person> personList=personRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }
}
