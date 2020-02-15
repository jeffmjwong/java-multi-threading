package com.pluralsight.javamultithreading;

@ProcessedBy(AccountWorker.class)
public class BankAccount {
    private final String id;
    private int balance = 0;

    public String getId() {
        return id;
    }
    public int getBalance() {
        return balance;
    }

    public BankAccount(String id) {
        this.id = id;
    }
    public BankAccount(String id, int balance) {
        this(id);
        this.balance = balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdrawal(int amount) {
        balance -= amount;
    }
}
