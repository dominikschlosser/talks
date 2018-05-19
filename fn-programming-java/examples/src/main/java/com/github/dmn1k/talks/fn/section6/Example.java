package com.github.dmn1k.talks.fn.section6;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Example {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> getFromExternalService())
                .thenApply(r -> "Resultat: " + r);

        String result = future.get();

        System.out.println("Nach Call");

        System.out.println(result);
    }

    private static String getFromExternalService(){
        System.out.println("Call Webservice");
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "resultat von webservice";
    }

}
