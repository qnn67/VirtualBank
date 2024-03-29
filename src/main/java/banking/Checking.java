package banking;

public class Checking extends Account {
    public Checking(int id, Double apr) {
        super(id, apr);
        this.balance = 0;
        this.type = "checking";
    }

    @Override
    public boolean isAmountValidForDeposit(double amount) {
        if (amount >= 0 && amount <= 1000) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAccountWithdrawable() {
        return true;
    }

}
