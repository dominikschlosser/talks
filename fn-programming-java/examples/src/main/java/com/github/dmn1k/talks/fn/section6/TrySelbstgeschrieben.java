package com.github.dmn1k.talks.fn.section6;

import com.github.dmn1k.talks.fn.section6.types.Try;


public class TrySelbstgeschrieben {
    public static void main(String[] args) {
        String result = Try.ofFailable(() -> loadFromDatabase())
                .orElseTry(TrySelbstgeschrieben::loadFallbackValue)
                .map(input -> "Wert: " + input)
                .orElse("Wert nicht vorhanden");

        System.out.println(result);
    }


    // seiteneffektbehaftet, kann fehlschlagen
    private static String loadFromDatabase() {
        throw new RuntimeException("db ex");
    }

    // seiteneffektbehaftet, kann fehlschlagen
    private static String loadFallbackValue() {
        return "fallback wert";
    }
}
