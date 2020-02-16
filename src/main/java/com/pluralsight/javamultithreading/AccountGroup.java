package com.pluralsight.javamultithreading;

import java.util.HashMap;
import java.util.Map;

public class AccountGroup {
    private Map<String, BankAccount> accountMap = new HashMap<>();
    private int totalBalance;

    public int getTotalBalance() {
        return totalBalance;
    }

    public void addAccount(BankAccount bankAccount) {
        totalBalance += bankAccount.getBalance();
        accountMap.put(bankAccount.getId(), bankAccount);
    }
}
