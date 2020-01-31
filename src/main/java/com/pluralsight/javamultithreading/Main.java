package com.pluralsight.javamultithreading;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        runAdders();
    }

    private static void runAdders() {
        List<Adder> adders = new ArrayList<>();
        adders.add(new Adder("file1.txt", "fil1-out.txt"));
        adders.add(new Adder("file2.txt", "fil2-out.txt"));
        adders.add(new Adder("file3.txt", "fil3-out.txt"));
        adders.add(new Adder("file4.txt", "fil4-out.txt"));
        adders.add(new Adder("file5.txt", "fil5-out.txt"));
        adders.add(new Adder("file6.txt", "fil6-out.txt"));

        adders.forEach(a -> {
            try {
                a.doAdd();
            } catch (IOException e) {
                e.getMessage();
            }
        });
    }
}
