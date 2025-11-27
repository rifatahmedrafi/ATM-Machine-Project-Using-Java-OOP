package com.atm;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    // ✅ Encapsulated data
    private List<Account> accounts;
    private List<Transaction> transactions;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public Account validateLogin(String accountNumber, int pin) {
        Account account = findAccount(accountNumber);
        if (account != null && account.validatePin(pin)) {
            return account;
        }
        return null;
    }

    public void recordTransaction(Transaction transaction, Account account) {
        // ✅ Polymorphism: transaction.apply() calls correct subclass method
        transaction.apply(account);
        transactions.add(transaction);
    }

    public List<Transaction> getTransactionsForAccount(String accountNumber) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction txn : transactions) {
            if (txn.getAccountNumber().equals(accountNumber)) {
                result.add(txn);
            }
        }
        return result;
    }
}
