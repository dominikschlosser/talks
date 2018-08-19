package com.github.dmn1k.talks.fn.part1;

import com.github.dmn1k.talks.fn.part2.domain.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Example {

    public static class Person {
        private String firstName;
        private String lastName;
        private int age;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

    public static void main(String[] args) {
    }

    private List<String> filter(List<Person> people){
        return people.stream()
                .filter(person -> person.getFirstName().startsWith("Tom"))
                .filter(person -> person.getAge() > 30)
                .map(person -> person.getFirstName())
                .collect(Collectors.toList());
    }
}
