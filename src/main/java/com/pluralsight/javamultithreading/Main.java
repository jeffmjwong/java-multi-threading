package com.pluralsight.javamultithreading;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Java multi threading and concurrency!");

        try {
            List<String> numbers = Files.readAllLines(Paths.get("file1.txt"));
            System.out.println(numbers);
            int total = numbers
                    .stream()
                    .map(Integer::parseInt)
                    .reduce(Integer::sum)
                    .get();
            System.out.println(total);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
