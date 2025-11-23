package com.example.demo.repository;

import com.example.demo.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /*1. Native SQL*/
    @Query(value = "select * from users where email = :email", nativeQuery = true)//nativeQuery = true - execute Native SQL
    Optional<User> findByEmail(String email);//(@Param(value = "email") String email) - when different name in DB


    /* 2. JPQL (Java Persistence Query Language)
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);
     */

    /* 3. JPA Annotation
    * https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html
    *
    User findByEmailAndAgeAfterAAndBirth(String email, int age, Date birth);
     */
}
