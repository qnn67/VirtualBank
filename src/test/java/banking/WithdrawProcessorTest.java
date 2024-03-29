package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawProcessorTest {
    Bank bank;
    WithdrawProcessor withdrawProcessor;


    @BeforeEach
    void SetUp() {
        bank = new Bank();
        withdrawProcessor = new WithdrawProcessor(bank);
        bank.addChecking(12345678, 1);
        bank.addSavings(12345678 + 1, 1);
        bank.addCD(12345678 + 2, 1, 1000);
    }

    @Test
    void valid_command() {
        withdrawProcessor.execute("withdraw 12345678 100");
        assertEquals(0, bank.getAccounts().get(12345678).getBalance());
    }

    @Test
    void valid_command_where_only_withdraw_amount_available_in_account() {
        bank.getAccounts().get(12345678).deposit(50);
        withdrawProcessor.execute("withdraw 12345678 100");
        assertEquals(0, bank.getAccounts().get(12345678).getBalance());
    }

    @Test
    void valid_command_where_enough_time_has_passed_for_savings_account() {
        bank.getAccounts().get(12345679).addTime(1);
        withdrawProcessor.execute("withdraw 12345679 100");
        assertEquals(0, bank.getAccounts().get(12345679).getBalance());
    }


}
