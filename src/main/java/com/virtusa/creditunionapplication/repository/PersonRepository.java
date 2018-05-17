package com.virtusa.creditunionapplication.repository;

import com.virtusa.creditunionapplication.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private List<Person> persons = new ArrayList<>();

    public void addPerson(Person person) {
        persons.add(person);
    }

    public boolean isEligible(Person input) {
        Person person = persons.stream().filter(p -> p.equals(input)).findFirst().orElse(new Person());
        return person.isEligibility();
    }

    public Person getPersonBySSN(String ssn) {
        return persons.stream().filter(person -> person.getSsn().equals(ssn)).findAny().orElseThrow(() -> new RuntimeException("Person not found"));
    }
}
