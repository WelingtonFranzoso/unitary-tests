package com.franzoso.service;

import com.franzoso.entities.Person;
import com.franzoso.exceptions.BusinessException;
import com.franzoso.repositories.PersonRepository;
import com.franzoso.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @InjectMocks
    PersonService service;

    @Mock
    PersonRepository repository;

    Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("Angelica", "12358569852", "Desenvolvedora", 30, "Sao Paulo", "Rua das Cruzes", 54);
    }

    @Test
    void shouldSuccessfullySearchForPeopleBySocialSecurityNumber() {
        when(repository.findPerson(person.getSocialSecurityNumber())).thenReturn(Collections.singletonList(person));

        List<Person> people = service.findPersonBySocialSecurityNumber(person.getSocialSecurityNumber());

        assertEquals(Collections.singletonList(person), people);
        verify(repository).findPerson(person.getSocialSecurityNumber());
        verifyNoMoreInteractions(repository);

    }

    @Test
    void shouldNotCallRepositoryIfSocialSecurityNumberParameterIsNull(){
        final BusinessException e = assertThrows(BusinessException.class, () -> {
            service.findPersonBySocialSecurityNumber(null);
        });

        assertThat(e, notNullValue());
        assertThat(e.getMessage(), is("Error searching for people by Social Security Number = null"));
        assertThat(e.getCause(), notNullValue());
        assertThat(e.getCause().getMessage(), is("Social Security Number is mandatory!"));
        verifyNoInteractions(repository);

    }

    @Test
    void shouldThrowExceptionWhenRepositoryFails() {
        when(repository.findPerson(person.getSocialSecurityNumber())).thenThrow(new RuntimeException("Failed to search for people by Social Security Number!"));

        final BusinessException e = assertThrows(BusinessException.class, () -> {
            service.findPersonBySocialSecurityNumber(person.getSocialSecurityNumber());
        });

        assertThat(e.getMessage(), is(format("Error searching for people by Social Security Number = %s", person.getSocialSecurityNumber())));
        assertThat(e.getCause().getClass(), is(RuntimeException.class));
        assertThat(e.getCause().getMessage(), is("Failed to search for people by Social Security Number!"));
        verify(repository).findPerson(person.getSocialSecurityNumber());
        verifyNoMoreInteractions(repository);

    }



}
