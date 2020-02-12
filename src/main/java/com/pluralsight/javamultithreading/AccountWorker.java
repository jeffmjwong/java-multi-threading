package com.pluralsight.javamultithreading;

public class AccountWorker implements Runnable {
    BankAccount bankAccount;
    HighVolumeAccount highVolumeAccount;

    public AccountWorker(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
    public AccountWorker(HighVolumeAccount highVolumeAccount) {
        this.highVolumeAccount = highVolumeAccount;
    }

    public void doWork() {
        Thread t = new Thread(highVolumeAccount != null ? highVolumeAccount : this);
        t.start();
    }

    @Override
    public void run() {
        char transactionType = 'd';
        int amount = 100;

        if (transactionType == 'w') {
            bankAccount.withdrawal(amount);
        } else {
            bankAccount.deposit(amount);
        }
    }
}
