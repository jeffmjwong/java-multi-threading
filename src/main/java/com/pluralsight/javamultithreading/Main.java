package com.pluralsight.javamultithreading;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
//        runAdders();
//        runBankAccounts();

        BankAccount account1 = new BankAccount("1", 500);
        doWork(account1);
        Class<BankAccount> c = BankAccount.class;
        showName(c);
    }

    private static void doWork(Object obj) {
        Class<?> c = obj.getClass();
        showName(c);
    }

    private static void showName(Class<?> theClass) {
        System.out.println(theClass.getSimpleName());
    }

    private static void runBankAccounts() {
        ExecutorService es = Executors.newFixedThreadPool(5);

        BankAccount bankAccount = new BankAccount("1", 100);

        for (int i = 0; i < 5; i++) {
            Worker worker = new Worker(bankAccount);
            es.submit(worker);
        }

        try {
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void runAdders() {
        String[] inFiles = { "file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt" };
        String[] outFiles = { "file1-out.txt", "file2-out.txt", "file3-out.txt", "file4-out.txt", "file5-out.txt", "file6-out.txt" };

        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Integer>[] results = new Future[inFiles.length];

        for (int i = 0; i < inFiles.length; i++) {
            final Adder adder = new Adder(inFiles[i], outFiles[i]);
            results[i] = es.submit(adder);
        }

        for (Future<Integer> result : results) {
            try {
                int value = result.get();
                System.out.println(value);
            } catch (ExecutionException e) {
                Throwable adderException = e.getCause();
                System.out.println(adderException.getMessage());
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }
}
