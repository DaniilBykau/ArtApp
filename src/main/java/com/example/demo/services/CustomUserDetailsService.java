package com.example.demo.services;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public CustomUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findPersonByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Person not found with username: " + username));
        return build(person);
    }

    public Person loadPersonById(Long id) {
        return personRepository.findPersonById(id).orElse(null);
    }

    public static Person build(Person person) {

        List<GrantedAuthority> authorities = person.getRoles().stream().map(role ->
            new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());

        return new Person(
                person.getId(),
                person.getUsername(),
                person.getEmail(),
                person.getPassword(),
                authorities);
    }
}
