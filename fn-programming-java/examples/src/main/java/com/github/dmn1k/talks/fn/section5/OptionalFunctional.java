package com.github.dmn1k.talks.fn.section5;

import com.github.dmn1k.talks.fn.section5.domain.functional.Person;

import java.util.Optional;

@SuppressWarnings("ALL")
public class OptionalFunctional {
    public static void main(String[] args) {
        new OptionalFunctional().getCarInsurance(Optional.empty());

    }

    String getCarInsurance(Optional<Person> person){
        return person.flatMap(p -> p.getCar())
                     .flatMap(car -> car.getInsurance())
                     .map(insurance -> insurance.getName())
                     .orElse("Unknown");
    }
}
