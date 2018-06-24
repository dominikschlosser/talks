package com.github.dmn1k.talks.fn.part2.domain.functional;

import java.util.Optional;

public class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}
