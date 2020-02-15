package com.pluralsight.javamultithreading;

@WorkHandler(false)
public class AccountWorker implements Runnable, TaskWorker {
    BankAccount bankAccount;

    @Override
    public void setTarget(Object target) {
        if (target instanceof BankAccount) {
            bankAccount = (BankAccount) target;
        } else {
            throw new IllegalArgumentException("Target is not an instance of Bank Account!");
        }
    }

    @Override
    public void doWork() {
        Thread t = new Thread(bankAccount instanceof HighVolumeAccount ? (HighVolumeAccount) bankAccount : this);
        t.start();
    }

    @Override
    public void run() {
        char transactionType = 'd';
        int amount = 100;

        if (transactionType == 'd') {
            bankAccount.withdrawal(amount);
        } else {
            bankAccount.deposit(amount);
        }
    }
}
