package com.pluralsight.javamultithreading;

public class HighVolumeAccount extends BankAccount {
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
}
