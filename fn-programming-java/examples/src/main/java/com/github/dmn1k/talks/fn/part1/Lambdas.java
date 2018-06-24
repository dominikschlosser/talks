package com.github.dmn1k.talks.fn.part1;


public class Lambdas {
    interface Function<I, O> {
        O apply(I input);
    }

    public static void main(String[] args) {
        Function<Integer, Boolean> isEven = input -> input % 2 == 0;
    }
}
