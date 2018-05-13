package com.github.dmn1k.talks.fn.section6;

import com.github.dmn1k.talks.fn.section6.types.Try;


public class TrySelbstgeschrieben {
    public static void main(String[] args) {
        String result = Try.ofFailable(() -> loadFromDatabase())
                .map(input -> input + "mapped")
                .orElse("Wert nicht vorhanden");

        System.out.println(result);
    }


    // seiteneffektbehaftet, kann fehlschlagen
    private static String loadFromDatabase() {
        return "wert aus datenbank";
    }
}
