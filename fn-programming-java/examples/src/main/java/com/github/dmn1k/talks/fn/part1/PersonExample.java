package com.github.dmn1k.talks.fn.part1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PersonExample {
    private static class Person {
        private String name;
        private String firstName;

        public String getFirstName() {
            return firstName;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> names = new ArrayList<Person>().parallelStream()
                .sorted(Comparator.comparing(p -> p.getFirstName()))
                .map(p -> p.getName())
                .collect(Collectors.toList());
    }
}
