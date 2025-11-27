package com.atm;

public class ATMMain {

    public static void main(String[] args) {

        // Create bank and some sample accounts
        Bank bank = new Bank();

        bank.addAccount(new Account("1001", "Rifat Ahmed Rafi", 1234, 5000.0));
        bank.addAccount(new Account("1002", "Alice", 1111, 3000.0));
        bank.addAccount(new Account("1003", "Bob", 2222, 10000.0));

        // Create ATM and start it
        ATM atm = new ATM(bank);
        atm.start();
    }
}
