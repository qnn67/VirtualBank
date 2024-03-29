package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositValidatorTest {
    DepositValidator depositValidator;
    Bank bank;

    @BeforeEach
    void SetUp() {
        bank = new Bank();
        depositValidator = new DepositValidator(bank);
        bank.addChecking(12345678, 0.01);
        bank.addSavings(12345678 + 1, 0.01);
        bank.addCD(12345678 + 2, 0.01, 1000);
    }

    @Test
    void valid_deposit_checking_command() {
        boolean actual = depositValidator.validate("deposit 12345678 100");
        assertTrue(actual);
    }

    @Test
    void valid_deposit_savings_command() {
        boolean actual = depositValidator.validate("deposit 12345679 100");
        assertTrue(actual);
    }

    @Test
    void valid_command_where_uppercase_is_included() {
        boolean actual = depositValidator.validate("DepOsIt 12345679 100");
        assertTrue(actual);
    }

    @Test
    void invalid_deposit_cd_command() {
        boolean actual = depositValidator.validate("deposit 12345680 100");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_deposit_is_missing() {
        boolean actual = depositValidator.validate("12345678 100");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_where_deposit_is_misspelled() {
        boolean actual = depositValidator.validate("deposti 12345678 100");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_where_deposit_is_not_a_number() {
        boolean actual = depositValidator.validate("111 12345678 100");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_where_id_is_missing() {
        boolean actual = depositValidator.validate("deposit 100");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_where_id_is_not_a_number() {
        boolean actual = depositValidator.validate("deposit abcdefgh 100");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_where_id_length_is_not_8() {
        boolean actual = depositValidator.validate("deposit 1234567 100");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_where_amount_is_missing() {
        boolean actual = depositValidator.validate("deposit 12345678");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_where_account_is_not_in_bank() {
        boolean actual = depositValidator.validate("deposit 88888888 100");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_where_amount_is_0() {
        boolean actual = depositValidator.validate("deposit 12346578 0");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_checking_command_where_amount_is_more_than_1000() {
        boolean actual = depositValidator.validate("deposit 12346578 1001");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_savings_command_where_amount_is_more_than_2500() {
        boolean actual = depositValidator.validate("deposit 12346578 2501");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_where_amount_is_negative() {
        boolean actual = depositValidator.validate("deposit 12346578 -1");
        assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_where_amount_is_not_a_number() {
        boolean actual = depositValidator.validate("deposit 12346578 abc");
        assertFalse(actual);
    }

}
