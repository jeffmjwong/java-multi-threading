package com.pluralsight.javamultithreading;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Adder implements Runnable {
    private String inFile;
    private String outFile;

    public Adder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void doAdd() throws IOException {
        int total = 0;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(inFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                total += Integer.parseInt(line);
            }
        }

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(outFile))) {
            bw.write("Total: " + total);
        }
    }

    public void run() {
        try {
            doAdd();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
