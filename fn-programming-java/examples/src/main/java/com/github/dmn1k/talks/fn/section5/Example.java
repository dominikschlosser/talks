package com.github.dmn1k.talks.fn.section5;




import com.github.dmn1k.talks.fn.section5.domain.functional.Person;

import java.util.Optional;

public class Example {
    public static void main(String[] args) {
        System.out.println(getInsuranceName(Optional.empty()));
    }

    public static String getInsuranceName(Optional<Person> person) {
        return person.flatMap(p -> p.getCar())
                    .flatMap(c -> c.getInsurance())
                    .map(c -> c.getName())
                    .orElse("Unknown");
    }
}
