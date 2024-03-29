package banking;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected int id;
    protected double balance;
    protected double apr;
    protected String type;
    protected int time_passed = 0;
    protected List<String> transaction_history = new ArrayList<>();
    protected boolean withdrawed_this_month = false;

    public Account(int id, double apr) {
        this.id = id;
        this.apr = apr;

    }

    public boolean isAmountValidForDeposit(double amount) {
        return true;
    }

    public int getId() {
        return this.id;
    }

    public double getApr() {
        return this.apr;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public String getType() {
        return this.type;
    }

    public double withdraw(double amount) {
        withdrawed_this_month = true;
        if (amount > balance) {
            double actual_amount = balance;
            balance = balance - actual_amount;
            return actual_amount;
        } else {
            balance -= amount;
            return amount;
        }
    }

    public void addTime(int month) {
        time_passed += month;
        withdrawed_this_month = false;
    }

    public List<String> getTransactionHistory() {
        return transaction_history;
    }

    public void addToTransactionHistory(String command) {
        transaction_history.add(command);
    }

    public abstract boolean isAccountWithdrawable();

}
