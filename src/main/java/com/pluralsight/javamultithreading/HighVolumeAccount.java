package com.pluralsight.javamultithreading;

public final class HighVolumeAccount extends BankAccount implements Runnable {
    public HighVolumeAccount(String id) {
        super(id);
    }
    public HighVolumeAccount(String id, int balance) {
        super(id, balance);
    }

    private int[] readDailyDeposits() {
        return new int[]{1, 2, 3};
    }

    private int[] readDailyWithdrawals() {
        return new int[]{1, 2, 3};
    }

    @Override
    public void run() {
        for (int depositAmount : readDailyDeposits()) {
            deposit(depositAmount);
        }

        for (int withdrawalAmount : readDailyWithdrawals()) {
            withdrawal(withdrawalAmount);
        }
    }
}
