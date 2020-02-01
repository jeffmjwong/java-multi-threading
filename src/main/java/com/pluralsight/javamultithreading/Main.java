package com.pluralsight.javamultithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        runAdders();
    }

    private static void runAdders() {
        String[] inFiles = { "file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt" };
        String[] outFiles = { "file1-out.txt", "file2-out.txt", "file3-out.txt", "file4-out.txt", "file5-out.txt", "file6-out.txt" };

        ExecutorService es = Executors.newFixedThreadPool(3);

        for (int i = 0; i < inFiles.length; i++) {
            final Adder adder = new Adder(inFiles[i], outFiles[i]);
            es.submit(adder);
        }

        try {
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
