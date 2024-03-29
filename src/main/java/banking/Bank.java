package banking;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public Map<Integer, Account> getAccounts() {
        return this.accounts;
    }

    public void addChecking(int id, double apr) {
        Account checking = new Checking(id, apr);
        accounts.put(id, checking);
    }

    public void addSavings(int id, double apr) {
        Account savings = new Savings(id, apr);
        accounts.put(id, savings);
    }

    public void addCD(int id, double apr, double amount) {
        Account cd = new CD(id, apr, amount);
        accounts.put(id, cd);
    }

    public boolean account_exists(int id) {
        Account account = accounts.get(id);
        if (account != null) {
            return true;
        } else {
            return false;
        }
    }

    public void removeAccount(int id) {
        accounts.remove(id);
    }
}
