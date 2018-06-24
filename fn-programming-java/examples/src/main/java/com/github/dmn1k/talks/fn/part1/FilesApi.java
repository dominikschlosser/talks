package com.github.dmn1k.talks.fn.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class FilesApi {
    public static void main(String[] args) throws IOException {
        Files.walk(Paths.get("src/main/resources/files"))
                .filter(Files::isRegularFile)
                .sorted(Comparator.comparing(FilesApi::fileSize).reversed())
                .map(file -> file.getFileName() + " - " + fileSize(file))
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
