package com.springsecurity.service;

import com.springsecurity.doa.JournalDao;
import com.springsecurity.model.Journal;
import com.springsecurity.model.Person;
import com.springsecurity.repository.JournalRepository;
import com.springsecurity.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService{
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private JournalRepository journalRepository;

    @Override
    public void createJournal(JournalDao journalDao) {
        Journal journal=new Journal();
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        Optional<Person> person=personRepository.findByName(userName);
        if(person.isPresent()){
            Person person1=person.get();
                journal.setTitle(journalDao.getTitle());
                journal.setDate(LocalDateTime.now());
                journal.setContent(journalDao.getContent());
                journal.setPerson(person1);
                List<Journal> journalList=person1.getJournalList();
                if(journalList==null){
                    journalList=new ArrayList<>();
                }
                journalList.add(journal);
                person1.setJournalList(journalList);
                journalRepository.save(journal);
        }else {
                throw new RuntimeException("Journal is not created");
            }
        }

    @Override
    public List<Journal> viewAllJournal() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
       Optional<Person> person=personRepository.findByName(userName);
        if(person.isPresent()){
            Person oldPerson=person.get();
            return journalRepository.findByPerson(oldPerson);
        }else {
            throw new UsernameNotFoundException("Journal is empty");
        }
    }

    @Override
    public Journal findByTitle(String title) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        Optional<Person> person=personRepository.findByName(userName);
        if(person.isPresent())
        {
            return journalRepository.findByTitle(title);
        }else {
            throw new RuntimeException("There is no book of this Title name: "+title);
        }
    }

    @Override
    public Journal updateJournal(Long id,JournalDao journalDao) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        Optional<Person> person=personRepository.findByName(userName);
        if(person.isPresent()){
            Person oldPerson=person.get();
            Optional<Journal> optionalJournal=journalRepository.findById(id);
            if(optionalJournal.isPresent()){
                Journal journal=optionalJournal.get();
                if(journal.getPerson().getId().equals(oldPerson.getId())){
                  if(journalDao.getTitle()!=null && !journalDao.getTitle().isEmpty()){
                      journal.setTitle(journalDao.getTitle());
                  }
                    if(journalDao.getContent()!=null && !journalDao.getContent().isEmpty()){
                        journal.setContent(journalDao.getContent());
                    }
                   journal.setDate(LocalDateTime.now());
                    journalRepository.save(journal);
                }
                return journal;
            }
        }
       throw new RuntimeException("Journal not found with id : "+ id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        journalRepository.deleteById(id);
    }


}
