package com.pluralsight.javamultithreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

public class Adder implements Callable<Integer> {
    private String inFile;
    private String outFile;

    public Adder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public int doAdd() throws IOException {
        int total = 0;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(inFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                total += Integer.parseInt(line);
            }
        }

        return total;
    }

    public Integer call() throws IOException {
        return doAdd();
    }
}
