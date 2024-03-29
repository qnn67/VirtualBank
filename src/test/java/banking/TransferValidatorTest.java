package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferValidatorTest {
    TransferValidator transferValidator;
    Bank bank;

    @BeforeEach
    void SetUp() {
        bank = new Bank();
        transferValidator = new TransferValidator(bank);
        bank.addChecking(12345678, 1);
        bank.addSavings(12345678 + 1, 1);
        bank.addCD(12345678 + 2, 1, 1000);
    }

    @Test
    void valid_command() {
        Boolean actual = transferValidator.validate("transfer 12345678 12345679 100");
        assertTrue(actual);
    }

    @Test
    void valid_command_where_uppercase_is_used() {
        Boolean actual = transferValidator.validate("trAnSFer 12345678 12345679 100");
        assertTrue(actual);
    }

    @Test
    void invalid_command_where_transfer_is_spelled_incorrectly() {
        Boolean actual = transferValidator.validate("transf 12345678 12345699 100");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_account_does_not_exist_in_bank() {
        Boolean actual = transferValidator.validate("transfer 12345678 12345699 100");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_target_account_is_CD() {
        Boolean actual = transferValidator.validate("transfer 12345678 12345680 100");
        assertFalse(actual);
    }

}
