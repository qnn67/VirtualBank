package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeValidatorTest {
    PassTimeValidator passTimeValidator;
    Bank bank;

    @BeforeEach
    void SetUp() {
        bank = new Bank();
        passTimeValidator = new PassTimeValidator(bank);
        bank.addChecking(12345678, 1);
        bank.getAccounts().get(12345678).deposit(100);
    }

    @Test
    void valid_command() {
        Boolean actual = passTimeValidator.validate("pass 1");
        assertTrue(actual);
    }

    @Test
    void valid_command_where_uppercase_is_used() {
        Boolean actual = passTimeValidator.validate("pAsS 1");
        assertTrue(actual);
    }

    @Test
    void invalid_command_where_pass_is_spelled_incorrectly() {
        Boolean actual = passTimeValidator.validate("pss 1");
        assertFalse(actual);
    }

    @Test
    void invalid_command_to_pass_zero_month() {
        Boolean actual = passTimeValidator.validate("pass 0");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_number_of_months_is_more_than_60() {
        Boolean actual = passTimeValidator.validate("pass 61");
        assertFalse(actual);
    }

    @Test
    void invalid_command_to_pass_decimal_as_number_of_months() {
        Boolean actual = passTimeValidator.validate("pass 1.0");
        assertFalse(actual);
    }


}
