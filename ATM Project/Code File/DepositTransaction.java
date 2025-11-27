package com.atm;

public class DepositTransaction extends Transaction {

    public DepositTransaction(String accountNumber, double amount) {
        super(accountNumber, amount);
    }

    @Override
    public void apply(Account account) {
        account.deposit(getAmount());
    }

    @Override
    public String getType() {
        return "DEPOSIT";
    }
}
