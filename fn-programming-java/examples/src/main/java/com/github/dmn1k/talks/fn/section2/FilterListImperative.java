package com.github.dmn1k.talks.fn.section2;

import java.util.ArrayList;
import java.util.List;

public class FilterListImperative {
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            input.add(i);
        }

        List<Integer> result = getOnlyEvenNumbers(input);

        for (Integer number : result) {
            System.out.println(number);
        }
    }

    private static List<Integer> getOnlyEvenNumbers(List<Integer> input) {
        List<Integer> result = new ArrayList<>();
        for (Integer number : input) {
            if(number % 2 == 0){
                result.add(number);
            }

        }

        return result;
    }

}
