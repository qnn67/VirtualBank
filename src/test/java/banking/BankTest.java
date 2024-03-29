package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {
    public static int id = 12345678;
    public static double apr = 0.2;
    public static int amount = 1000;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void bank_has_no_account_initially() {
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    void check_id_when_adding_one_account_to_bank() {
        bank.addSavings(id, apr);
        assertEquals(id, bank.getAccounts().get(id).getId());
    }

    @Test
    void check_id_when_adding_two_accounts_to_bank() {
        bank.addSavings(this.id, this.apr);
        bank.addCD(this.id + 1, this.apr, amount);
        assertEquals(this.id, bank.getAccounts().get(this.id).getId());
        assertEquals(this.id + 1, bank.getAccounts().get(this.id + 1).getId());
    }

    @Test
    void check_apr_when_adding_one_account_to_bank() {
        bank.addCD(id, apr, amount);
        assertEquals(apr, bank.getAccounts().get(id).getApr());
    }

    @Test
    void check_apr_when_adding_two_accounts_to_bank() {
        bank.addSavings(this.id, this.apr);
        bank.addChecking(this.id + 1, this.apr);
        assertEquals(apr, bank.getAccounts().get(this.id).getApr());
        assertEquals(apr, bank.getAccounts().get(this.id + 1).getApr());
    }

    @Test
    void check_balance_when_depositing_to_account() {
        bank.addChecking(this.id, this.apr);
        bank.getAccounts().get(this.id).deposit(100);
        assertEquals(100, bank.getAccounts().get(this.id).getBalance());
    }

    @Test
    void check_balance_when_withdrawing_from_account() {
        bank.addChecking(this.id, this.apr);
        bank.getAccounts().get(this.id).deposit(100);
        bank.getAccounts().get(this.id).withdraw(100);
        assertEquals(0, bank.getAccounts().get(this.id).getBalance());
    }

}
