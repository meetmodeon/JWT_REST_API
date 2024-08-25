package com.springsecurity.controller;

import com.springsecurity.doa.PersonDao;
import com.springsecurity.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signin")
@CrossOrigin
public class SigninController {
    @Autowired
    private PersonServiceImpl personServiceImpl;
    @PostMapping()
    public ResponseEntity<String> createPerson(@RequestBody PersonDao personDao){
        personServiceImpl.createPerson(personDao);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created successfully!!");
    }
}
