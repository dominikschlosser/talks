package com.github.dmn1k.talks.fn.section6;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class JavaObservables {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(JavaObservables::loadFromExternalService)
                .exceptionally(ex -> "exception occured")
                .thenAccept(wert -> System.out.println("Wert erhalten: " + wert));

        System.out.println("Message aus main thread");

        future.get();

    }

    private static String loadFromExternalService() {
        System.out.println("Message aus background thread");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "wert aus service";
    }
}
