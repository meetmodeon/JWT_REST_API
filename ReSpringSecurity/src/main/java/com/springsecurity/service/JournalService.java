package com.springsecurity.service;

import com.springsecurity.doa.JournalDao;
import com.springsecurity.model.Journal;

import java.util.List;

public interface JournalService {
    public void createJournal(JournalDao journalDao);
    public List<Journal> viewAllJournal();
    public Journal findByTitle(String title);
    public Journal updateJournal(Long id,JournalDao journalDao);
    public void deleteById(Long id);
}
