package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassTimeProcessorTest {
    PassTimeProcessor passTimeProcessor;
    Bank bank;

    @BeforeEach
    void SetUp() {
        bank = new Bank();
        passTimeProcessor = new PassTimeProcessor(bank);
        bank.addChecking(12345678, 1);
        bank.getAccounts().get(12345678).deposit(100);
    }

    @Test
    void valid_command_to_pass_a_month() {
        passTimeProcessor.execute("pass 1");
        Double actual = bank.getAccounts().get(12345678).getBalance();
        assertEquals(100.08333333333333, actual);
    }

    @Test
    void valid_command_to_pass_two_months() {
        passTimeProcessor.execute("pass 2");
        Double actual = bank.getAccounts().get(12345678).getBalance();
        assertEquals(100.1667361111111, actual);
    }

    @Test
    void valid_command_to_pass_60_months() {
        passTimeProcessor.execute("pass 60");
        Double actual = bank.getAccounts().get(12345678).getBalance();
        actual = Double.valueOf(String.format("%.2f", actual));
        assertEquals(105.12, actual);
    }

    @Test
    void check_if_account_gets_deleted_if_balance_is_zero() {
        bank.getAccounts().get(12345678).withdraw(100);
        passTimeProcessor.execute("pass 1");
        Account actual = bank.getAccounts().get(12345678);
        assertEquals(null, actual);
    }

    @Test
    void check_if_account_is_fined_when_balance_is_below_100() {
        bank.getAccounts().get(12345678).withdraw(1);
        passTimeProcessor.execute("pass 1");
        Double actual = bank.getAccounts().get(12345678).getBalance();
        assertEquals(74.06166666666667, actual);
    }

    @Test
    void check_if_account_is_fined_for_every_month_passed_when_balance_is_below_100() {
        bank.getAccounts().get(12345678).withdraw(1);
        passTimeProcessor.execute("pass 3");
        Double actual = bank.getAccounts().get(12345678).getBalance();
        assertEquals(24.12263684837963, actual);
    }

    @Test
    void check_if_cd_account_gets_accrued_3_times_per_month() {
        bank.addCD(12345678 + 1, 1, 1000);
        passTimeProcessor.execute("pass 1");
        Double actual = bank.getAccounts().get(12345678 + 1).getBalance();
        assertEquals(1000.8333333333334, actual);
    }
}
