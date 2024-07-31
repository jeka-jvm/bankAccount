package org.example;

public class BankAccount {

    private int balance;
    private final int accountNumber;

    public BankAccount(int accountNumber, int initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Недостаточно средств");
        }
    }

    public synchronized int getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
