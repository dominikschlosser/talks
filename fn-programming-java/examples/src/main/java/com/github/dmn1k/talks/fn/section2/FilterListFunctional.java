package com.github.dmn1k.talks.fn.section2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FilterListFunctional {
    public static void main(String[] args) {
        List<Integer> input = IntStream.rangeClosed(1, 100)
                .mapToObj(number -> number)
                .collect(Collectors.toList());


        List<Integer> result = getOnlyEvenNumbers(input);

        result.forEach(System.out::println);
    }

    private static List<Integer> getOnlyEvenNumbers(List<Integer> input){
        return input.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());
    }
}
