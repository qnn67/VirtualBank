package banking;

public class CD extends Account {
    public CD(int id, double apr, double amount) {
        super(id, apr);
        this.balance = amount;
        this.type = "cd";
    }

    @Override
    public boolean isAmountValidForDeposit(double amount) {
        return false;
    }

    @Override
    public boolean isAccountWithdrawable() {
        if (time_passed >= 12) {
            return true;
        } else {
            return false;
        }
    }

}
