package com.atm;

public class WithdrawTransaction extends Transaction {

    public WithdrawTransaction(String accountNumber, double amount) {
        super(accountNumber, amount);
    }

    @Override
    public void apply(Account account) {
        boolean success = account.withdraw(getAmount());
        if (!success) {
            System.out.println("Withdrawal transaction failed.");
        }
    }

    @Override
    public String getType() {
        return "WITHDRAW";
    }
}
