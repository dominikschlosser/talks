package com.github.dmn1k.talks.fn.section6;


import java.util.concurrent.CompletableFuture;

public class Example {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> callDb())
                .exceptionally(ex -> "default wert")
                .thenAccept(wert -> System.out.println(wert));
        System.out.println("Nach Aufruf");


    }
    private static String callDb(){
        try {
            Thread.sleep(5000L);

            return "wert aus db";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
