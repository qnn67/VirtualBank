package banking;

public class Savings extends Account {

    public Savings(int id, double apr) {
        super(id, apr);
        this.balance = 0;
        this.type = "savings";
    }

    @Override
    public boolean isAmountValidForDeposit(double amount) {
        if (amount >= 0 && amount <= 2500) {
            return true;
        }
        return false;
    }


    @Override
    public boolean isAccountWithdrawable() {
        if (withdrawed_this_month) {
            return false;
        }
        return true;
    }
}
