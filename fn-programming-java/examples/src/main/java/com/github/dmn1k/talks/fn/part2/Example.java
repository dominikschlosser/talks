package com.github.dmn1k.talks.fn.part2;


import com.github.dmn1k.talks.fn.part2.domain.functional.Person;

import java.util.Optional;

public class Example {
    public static String getInsuranceName(Optional<Person> person) {
        return person.flatMap(p -> p.getCar())
                .flatMap(c -> c.getInsurance())
                .map(i -> i.getName())
                .orElse("Keine Versicherung gefunden");
    }
}
