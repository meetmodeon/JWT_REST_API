package com.springsecurity.controller;

import com.springsecurity.doa.JournalDao;
import com.springsecurity.model.Journal;
import com.springsecurity.service.JournalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
@CrossOrigin
public class JournalController {
    @Autowired
    private JournalServiceImpl journalService;

    @PostMapping
    public ResponseEntity<?> createJournal(@RequestBody JournalDao journalDao){
        journalService.createJournal(journalDao);
        return ResponseEntity.status(HttpStatus.CREATED).body("The Journal is created successfully");
    }
    @GetMapping
    public ResponseEntity<List<Journal>> getAllJournal(){
         List<Journal>journalList=journalService.viewAllJournal();
         return ResponseEntity.status(HttpStatus.OK).body(journalList);
    }
    @PostMapping("/getBook")
    public ResponseEntity<Journal> getByTitle(@RequestBody Map<String,String> requestBody){
        String title=requestBody.get("title");
        Journal journal=journalService.findByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(journal);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Journal> updateJournal(@PathVariable Long id,@RequestBody JournalDao journalDao){
        Journal journal=journalService.updateJournal(id,journalDao);
        return ResponseEntity.status(HttpStatus.OK).body(journal);
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        journalService.deleteById(id);
        return "Delete Succefully which id have "+id;
    }

}
