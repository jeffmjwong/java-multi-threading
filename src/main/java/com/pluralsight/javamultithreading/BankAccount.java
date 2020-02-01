package com.pluralsight.javamultithreading;

public class BankAccount {
    private int balance;

    public int getBalance() {
        return balance;
    }

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }
}
