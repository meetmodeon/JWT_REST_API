package com.springsecurity.service;

import com.springsecurity.model.Person;
import com.springsecurity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       Optional<Person> person1=personRepository.findByName(userName);
       if(person1.isPresent()){
           Person person=person1.get();
            UserDetails userDetails=User.builder()
                   .username(person.getName())
                   .password(person.getPassword())
                   .roles(person.getRole().toString())
                   .build();
            return userDetails;
       }else {
           throw new UsernameNotFoundException("User not found with username"+userName);
       }

        }
}
