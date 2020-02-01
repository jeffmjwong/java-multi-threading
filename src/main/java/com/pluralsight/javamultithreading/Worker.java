package com.pluralsight.javamultithreading;

public class Worker implements Runnable {
    private BankAccount bankAccount;

    public Worker(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            final int startBalance = bankAccount.getBalance();
            System.out.println("Starting balance: " + startBalance);
            bankAccount.deposit(10);
            final int endBalance = bankAccount.getBalance();
            System.out.println("Ending balance: " + endBalance);
        }
    }
}
