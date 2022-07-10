package com.example.demo.repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findPersonById(Long id);

    Optional<Person> findPersonByUsername(String username);

    Optional<Person> findPersonByEmail(String email);
}
