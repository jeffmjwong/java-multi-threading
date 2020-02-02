package com.pluralsight.javamultithreading;

public class HighVolumeAccount extends BankAccount implements Runnable {
    public HighVolumeAccount(String id) {
        super(id);
    }
    public HighVolumeAccount(String id, int balance) {
        super(id, balance);
    }

    private int[] readDailyDeposits() {

    }

    private int[] readDailyWithdrawals() {

    }

    @Override
    public void run() {

    }
}
