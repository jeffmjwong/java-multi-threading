package com.pluralsight.javamultithreading;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
//        runAdders();
//        runBankAccounts();

//        BankAccount account = new BankAccount("1", 500);
//        account.deposit(300);
//        saveAccount(account, "account.dat");
//        BankAccount account2 = loadAccount("account.dat");
//        System.out.println(account2.getBalance());
//        System.out.println(account2.getId());
//        classInfo(account);
//        typeModifiers(account);
//        fieldInfo(account);
//        callGetId(account);
//        callDeposit(account, 50);
//        startWork("com.pluralsight.javamultithreading.AccountWorker", account);
//        startWorkSelfContained(account);

        BankAccount account1 = new BankAccount("1", 500);
        BankAccount account2 = new BankAccount("2", 200);
        AccountGroup accountGroup = new AccountGroup();
        accountGroup.addAccount(account1);
        accountGroup.addAccount(account2);

        saveGroup(accountGroup, "group.dat");

        AccountGroup accountGroup2 = loadGroup("group.dat");
        System.out.println(accountGroup2.getTotalBalance());
    }

    private static AccountGroup loadGroup(String filename) {
        AccountGroup accountGroup = null;

        try (ObjectInputStream objectStream = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            accountGroup = (AccountGroup) objectStream.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return accountGroup;
    }

    private static void saveGroup(AccountGroup accountGroup, String filename) {
        try (ObjectOutputStream objectStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            objectStream.writeObject(accountGroup);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static BankAccount loadAccount(String filename) {
        BankAccount bankAccount = null;

        try (ObjectInputStream objectStream = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            bankAccount = (BankAccount) objectStream.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return bankAccount;
    }

    private static void saveAccount(BankAccount bankAccount, String filename) {
        try(ObjectOutputStream objectStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            objectStream.writeObject(bankAccount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void startWorkSelfContained(Object workerTarget) {
        try {
            final Class<?> targetType = workerTarget.getClass();
            final ProcessedBy pb = targetType.getAnnotation(ProcessedBy.class);
            final Class<?> workerType = pb.value();
            final TaskWorker worker = (TaskWorker) workerType.newInstance();

            worker.setTarget(workerTarget);

            final WorkHandler wh = workerType.getAnnotation(WorkHandler.class);
            if (wh == null) {
                throw new IllegalArgumentException("No WorkHandler interface available!");
            }

            worker.doWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void startWork(String workerTypeName, Object workerTarget) {
        try {
            Class<?> workerType = Class.forName(workerTypeName);
            final TaskWorker worker = (TaskWorker) workerType.newInstance();
            worker.setTarget(workerTarget);

            final WorkHandler wh = workerType.getAnnotation(WorkHandler.class);
            if (wh == null) {
                throw new IllegalArgumentException("No WorkHandler interface available!");
            }

            worker.doWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void callDeposit(Object obj, int amount) {
        try {
            Class<?> theClass = obj.getClass();
            final Method m = theClass.getMethod("deposit", int.class);
            m.invoke(obj, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void callGetId(Object obj) {
        try {
            Class<?> theClass = obj.getClass();
            final Method m = theClass.getMethod("getId");
            final Object result = m.invoke(obj);

            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void fieldInfo(Object obj) {
        Class<?> theClass = obj.getClass();

        Field[] fields = theClass.getFields();
        displayFields(fields);

        Field[] declaredFields = theClass.getDeclaredFields();
        displayFields(declaredFields);
    }

    private static void displayFields(Field[] arr) {
        for (Field field : arr) {
            System.out.println(field.getName() + " : " + field.getType());
        }
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
