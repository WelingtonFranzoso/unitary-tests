package com.franzoso.repositories;

import com.franzoso.entities.Person;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {

    public List<Person> findPerson(String socialSecurityNumber) {
        List<Person> people = listPeople();
        return people.stream()
                .filter(Objects::nonNull)
                .filter(person -> person.getSocialSecurityNumber().equals(socialSecurityNumber))
                .collect(Collectors.toList());
    }


    private static List<Person> listPeople() {

        return Arrays.asList(
                new Person("Angelica", "12358569852", "Desenvolvedora", 30, "Sao Paulo", "Rua das Cruzes", 54),
                new Person("Maria", "54865897584", "Product Owner", 25, "Rio de Janeiro", "Rua dos Carneiros", 29),
                new Person("Pedro", "54887587458", "Segurança", 58, "Porto Alegre", "Rua dos Andradas", 94),
                new Person("Felipe", "21456358754", "Advogado", 42, "Maceió", "Rua Central", 7859),
                new Person("João", "54865932101", "Contador", 33, "São Paulo", "Rua das Hortências", 5587),
                new Person("Elder", "54802365741", "Servidor Público", 58, "Porto Alegre", "Avenida Mauá", 52)
        );
    }
}
