package com.pluralsight.javamultithreading;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Adder {
    private String inFile;
    private String outFile;

    public Adder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void add() throws IOException {
        int total = 0;

        try (BufferedReader bf = Files.newBufferedReader(Paths.get(inFile))) {
            String line;
            while ((line = bf.readLine()) != null) {
                final int number = Integer.parseInt(line);
                total += number;
            }
        }

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(outFile))) {
            bw.write(total);
        }
    }
}
