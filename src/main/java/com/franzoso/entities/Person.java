package com.franzoso.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;
    private String socialSecurityNumber;
    private String profession;
    private Integer age;
    private String city;
    private String street;
    private Integer number;
}
