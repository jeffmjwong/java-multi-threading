package com.pluralsight.javamultithreading;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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

        HighVolumeAccount account = new HighVolumeAccount("1", 500);
//        classInfo(account);
//        typeModifiers(account);
        fieldInfo(account);
    }

    private static void fieldInfo(Object obj) {
        Class<?> theClass = obj.getClass();

        Field[] fields = theClass.getFields();
        System.out.println(Arrays.toString(fields));
    }

    private static void typeModifiers(Object obj) {
        Class<?> theClass = obj.getClass();
        final int modifiers = theClass.getModifiers();
        System.out.println(Modifier.isFinal(modifiers));
        System.out.println(Modifier.isPublic(modifiers));
        System.out.println(Modifier.isPrivate(modifiers));
        System.out.println(Modifier.isProtected(modifiers));
        System.out.println(Modifier.isStatic(modifiers));
    }

    private static void classInfo(Object obj) {
        Class<?> theClass = obj.getClass();
        System.out.println(theClass.getSimpleName());

        Class<?> superclass = theClass.getSuperclass();
        System.out.println(superclass.getSimpleName());

        Class<?>[] interfaces = theClass.getInterfaces();
        for (Class<?> interfaceInstance : interfaces) {
            System.out.println(interfaceInstance.getSimpleName());
        }
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
