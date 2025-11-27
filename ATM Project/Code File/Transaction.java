package com.atm;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Transaction {

    private String transactionId;
    private String accountNumber;
    private double amount;
    private LocalDateTime dateTime;

    public Transaction(String accountNumber, double amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // ✅ Abstraction + Polymorphism: subclasses must implement this
    public abstract void apply(Account account);

    public abstract String getType();

    public String getSummary() {
        return "[" + dateTime + "] " + getType() +
                " | Acc: " + accountNumber +
                " | Amount: " + amount +
                " | TxnId: " + transactionId;
    }
}
