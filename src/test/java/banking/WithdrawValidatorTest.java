package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawValidatorTest {
    WithdrawValidator withdrawValidator;
    PassTimeProcessor passTimeProcessor;
    Bank bank;

    @BeforeEach
    void SetUp() {
        bank = new Bank();
        withdrawValidator = new WithdrawValidator(bank);
        passTimeProcessor = new PassTimeProcessor(bank);
        bank.addChecking(12345678, 1);
        bank.addSavings(12345678 + 1, 1);
        bank.addCD(12345678 + 2, 1, 1000);
    }

    @Test
    void valid_command() {
        Boolean actual = withdrawValidator.validate("withdraw 12345678 100");
        assertTrue(actual);
    }

    @Test
    void valid_command_where_uppercase_is_used() {
        Boolean actual = withdrawValidator.validate("WithDraw 12345678 100");
        assertTrue(actual);
    }

    @Test
    void valid_command_where_amount_is_zero() {
        Boolean actual = withdrawValidator.validate("withdraw 12345678 0");
        assertTrue(actual);
    }

    @Test
    void invalid_command_where_withdraw_is_spelled_incorrectly() {
        Boolean actual = withdrawValidator.validate("withdra 12345678 0");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_id_is_not_an_integer() {
        Boolean actual = withdrawValidator.validate("withdraw abcdefgh 0");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_id_length_is_not_8() {
        Boolean actual = withdrawValidator.validate("withdraw 1234567 0");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_id_is_not_in_bank() {
        bank.removeAccount(12345678);
        Boolean actual = withdrawValidator.validate("withdraw 12345678 100");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_not_enough_time_passed_for_savings_account() {
        bank.getAccounts().get(12345679).deposit(100);
        bank.getAccounts().get(12345679).withdraw(10);
        Boolean actual = withdrawValidator.validate("withdraw 12345679 100");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_not_enough_time_passed_for_cd_account() {
        Boolean actual = withdrawValidator.validate("withdraw 12345680 100");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_not_withdrawing_full_amount_for_cd_account() {
        Boolean actual = withdrawValidator.validate("withdraw 12345680 100");
        assertFalse(actual);
    }
}
