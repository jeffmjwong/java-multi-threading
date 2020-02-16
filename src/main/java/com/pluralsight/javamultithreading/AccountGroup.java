package com.pluralsight.javamultithreading;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AccountGroup implements Serializable {
    private Map<String, BankAccount> accountMap = new HashMap<>();
    private transient int totalBalance;

    public int getTotalBalance() {
        return totalBalance;
    }

    public void addAccount(BankAccount bankAccount) {
        totalBalance += bankAccount.getBalance();
        accountMap.put(bankAccount.getId(), bankAccount);
    }

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        totalBalance = accountMap.values()
                .stream()
                .mapToInt(BankAccount::getBalance)
                .sum();
    }
}
