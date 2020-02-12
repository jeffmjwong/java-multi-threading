package com.pluralsight.javamultithreading;

public class AccountWorker implements Runnable {
    BankAccount ba;
    HighVolumeAccount hva;

    public AccountWorker(BankAccount ba) {

    }
    public AccountWorker(HighVolumeAccount hva) {

    }

    public void doWork() {
        Thread t = new Thread(hva != null ? hva : this);
    }

    @Override
    public void run() {
        char transactionType = 'd';
        int amount = 100;

        if (transactionType == 'w') {
            ba.withdrawal(amount);
        } else {
            ba.deposit(amount);
        }
    }
}
