package com.pluralsight.javamultithreading;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        runAdders();
    }

    private static void runAdders() {
        String[] inFiles = { "file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt" };
        String[] outFiles = { "file1-out.txt", "file2-out.txt", "file3-out.txt", "file4-out.txt", "file5-out.txt", "file6-out.txt" };

        for (int i = 0; i < inFiles.length; i++) {
            final Adder adder = new Adder(inFiles[i], outFiles[i]);
            Thread thread = new Thread(adder);
            thread.start();
        }
    }
}
