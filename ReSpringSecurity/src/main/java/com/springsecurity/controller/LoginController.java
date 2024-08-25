package com.springsecurity.controller;

import com.springsecurity.doa.LoginDao;
import com.springsecurity.repository.PersonRepository;
import com.springsecurity.service.PersonUserDetailsServiceImpl;
import com.springsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PersonUserDetailsServiceImpl personUserDetailsService;
    @PostMapping
    public ResponseEntity<String> logIn(@RequestBody LoginDao loginDao){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDao.getUserName(),loginDao.getPassword()));
            UserDetails details =personUserDetailsService.loadUserByUsername(loginDao.getUserName());
            String jwt=jwtUtil.generateToken(details.getUsername());
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        }catch (Exception e){
            throw new UsernameNotFoundException("User not Found Exception "+e.getMessage());
        }
    }
}
