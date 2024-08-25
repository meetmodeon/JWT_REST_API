package com.springsecurity.controller;

import com.springsecurity.doa.PersonDao;
import com.springsecurity.model.Person;
import com.springsecurity.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class PersonController {
    @Autowired
    private PersonServiceImpl personServiceImpl;


    @GetMapping("/getAllperson")
    public ResponseEntity<List<Person>> getAllPerson(){
        List<Person> person=personServiceImpl.getAllPersonDetails();
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        personServiceImpl.DeletePerson(id);
        return ResponseEntity.noContent().build();
    }

}
