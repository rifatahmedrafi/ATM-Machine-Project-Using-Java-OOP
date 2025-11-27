package com.atm;

import java.util.List;
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("===== Welcome to Java ATM =====");

        boolean exit = false;

        while (!exit) {
            Account loggedInAccount = login();

            if (loggedInAccount != null) {
                runSession(loggedInAccount);
            } else {
                System.out.println("Login failed. Try again.");
            }

            System.out.print("Do you want to exit the ATM? (y/n): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                exit = true;
            }
        }

        System.out.println("Thank you for using Java ATM. Goodbye!");
    }

    private Account login() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        int pin = readInt();

        Account account = bank.validateLogin(accountNumber, pin);
        if (account != null) {
            System.out.println("Login successful. Welcome, " + account.getAccountHolderName() + "!");
        } else {
            System.out.println("Invalid account number or PIN.");
        }
        return account;
    }

    private void runSession(Account account) {
        boolean sessionActive = true;

        while (sessionActive) {
            showMenu();
            int choice = readInt();

            switch (choice) {
                case 1:
                    handleCheckBalance(account);
                    break;
                case 2:
                    handleDeposit(account);
                    break;
                case 3:
                    handleWithdraw(account);
                    break;
                case 4:
                    handleTransactionHistory(account);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    sessionActive = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n===== ATM Menu =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. View Transaction History");
        System.out.println("5. Logout");
        System.out.print("Select an option: ");
    }

    private void handleCheckBalance(Account account) {
        System.out.println("Your current balance is: " + account.checkBalance());
    }

    private void handleDeposit(Account account) {
        System.out.print("Enter amount to deposit: ");
        double amount = readDouble();

        Transaction txn = new DepositTransaction(account.getAccountNumber(), amount);
        bank.recordTransaction(txn, account);
    }

    private void handleWithdraw(Account account) {
        System.out.print("Enter amount to withdraw: ");
        double amount = readDouble();

        Transaction txn = new WithdrawTransaction(account.getAccountNumber(), amount);
        bank.recordTransaction(txn, account);
    }

    private void handleTransactionHistory(Account account) {
        List<Transaction> txns = bank.getTransactionsForAccount(account.getAccountNumber());
        if (txns.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("===== Transaction History =====");
            for (Transaction txn : txns) {
                System.out.println(txn.getSummary());
            }
        }
    }

    private int readInt() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.print("Please enter a valid integer: ");
            }
        }
    }

    private double readDouble() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
