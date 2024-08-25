package com.springsecurity.repository;

import com.springsecurity.model.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {


    Person findByEmail(String email);
    Optional<Person> findByName(String userName);

    @Transactional
    int deleteByName(String userName);

    @Query(value = "SELECT P FROM Person P WHERE P.userName= :userName AND P.password= :password",nativeQuery = true)
    boolean findByUserNameAndPassword(@Param("userName") String userName,@Param("password") String password);

}
