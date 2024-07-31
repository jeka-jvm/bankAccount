package org.example;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentBank {
    private final List<BankAccount> accounts = new ArrayList<>();
    private int nextAccountNumber = 1;

    public synchronized BankAccount createAccount(int initialBalance) {
        BankAccount account = new BankAccount(nextAccountNumber++, initialBalance);
        accounts.add(account);
        return account;
    }

    public void transfer(BankAccount fromAccount, BankAccount toAccount, int amount) {
        BankAccount firstLock, secondLock;

        if (fromAccount.getAccountNumber() < toAccount.getAccountNumber()) {
            firstLock = fromAccount;
            secondLock = toAccount;
        } else {
            firstLock = toAccount;
            secondLock = fromAccount;
        }

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (fromAccount.getBalance() >= amount) {
                    fromAccount.withdraw(amount);
                    toAccount.deposit(amount);
                }
            }
        }
    }

    public synchronized int getTotalBalance() {
        int totalBalance = 0;
        for (BankAccount account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }
}