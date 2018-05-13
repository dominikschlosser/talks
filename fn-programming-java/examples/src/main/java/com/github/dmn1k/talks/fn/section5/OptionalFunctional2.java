package com.github.dmn1k.talks.fn.section5;

import com.github.dmn1k.talks.fn.section5.domain.functional.Car;
import com.github.dmn1k.talks.fn.section5.domain.functional.Insurance;
import com.github.dmn1k.talks.fn.section5.domain.functional.Person;

import java.util.Optional;

public class OptionalFunctional2 {
    public static void main(String[] args) {
        new OptionalFunctional2().getCarInsurance(Optional.empty());

    }

    String getCarInsurance(Optional<Person> person){
        return person.flatMap(Person::getCar)
                     .flatMap(Car::getInsurance)
                     .map(Insurance::getName)
                     .orElse("Unknown");
    }
}
