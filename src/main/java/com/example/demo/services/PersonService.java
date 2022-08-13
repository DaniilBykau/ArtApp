package com.example.demo.services;

import com.example.demo.entity.Person;
import com.example.demo.entity.enums.ERole;
import com.example.demo.exeptions.PersonExistException;
import com.example.demo.payload.request.SignUpRequest;
import com.example.demo.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    public static final Logger LOG = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, BCryptPasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person createPerson(SignUpRequest personIn) {
        Person person = new Person();
        person.setEmail(personIn.getEmail());
        person.setFirstname(personIn.getFirstname());
        person.setLastname(personIn.getLastname());
        person.setUsername(personIn.getUsername());
        person.setPassword(passwordEncoder.encode(personIn.getPassword()));
        person.getRoles().add(ERole.ROLE_USER);

        try {
            LOG.info("Saving person {}", personIn.getEmail());
            return personRepository.save(person);
        } catch (Exception e) {
            LOG.error("Error during saving person. {}", e.getMessage());
            throw new PersonExistException("The person " + person.getEmail() + "already exist");
        }
    }
}
