package com.pluralsight.javamultithreading;

import java.io.Serializable;

@ProcessedBy(AccountWorker.class)
public class BankAccount implements Serializable {
    private final String id;
    private int balance = 0;
    private char lastTransactionType;
    private int lastTransactionAmount;

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
        lastTransactionType = 'd';
        lastTransactionAmount = amount;
    }

    public void withdrawal(int amount) {
        balance -= amount;
        lastTransactionType = 'w';
        lastTransactionAmount = amount;
    }
}
