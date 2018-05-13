package com.github.dmn1k.talks.fn.section4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class FilesApi {
    public static void main(String[] args) throws IOException {
        Files.walk(Paths.get("src/main/resources/files"))
                .sorted(Comparator.comparing(FilesApi::fileSize))
                .map(file -> file.getFileName())
                .forEach(System.out::println);
    }

    private static long fileSize(Path path){
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
