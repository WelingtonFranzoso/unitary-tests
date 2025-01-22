package com.franzoso.services;

import com.franzoso.entities.Person;
import com.franzoso.exceptions.BusinessException;
import com.franzoso.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.util.Assert.notNull;

@Service
@Slf4j
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findPersonBySocialSecurityNumber(String socialSecurityNumber) {
        try {
            notNull(socialSecurityNumber, "Social Security Number is mandatory!");

            return repository.findPerson(socialSecurityNumber);
        } catch (final Exception e) {
            throw new BusinessException(format("Error searching for people by Social Security Number = %s", socialSecurityNumber), e);
        }

    }

}
