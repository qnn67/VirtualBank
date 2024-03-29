package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateValidatorTest {
    Bank bank;
    CreateValidator createValidator;


    @BeforeEach
    void SetUp() {
        bank = new Bank();
        createValidator = new CreateValidator(bank);
    }

    @Test
    void valid_create_checking_command() {
        boolean actual = createValidator.validate("create checking 12345678 0.01");
        assertTrue(actual);
    }

    @Test
    void valid_create_savings_command() {
        boolean actual = createValidator.validate("create savings 12345678 0.01");
        assertTrue(actual);
    }

    @Test
    void valid_create_cd_command() {
        boolean actual = createValidator.validate("create cd 12345678 0.01 1000");
        assertTrue(actual);
    }

    @Test
    void valid_command_where_uppercase_is_included() {
        boolean actual = createValidator.validate("Create Savings 12345678 0.01");
        assertTrue(actual);
    }

    @Test
    void valid_command_where_apr_is_zero() {
        boolean actual = createValidator.validate("create savings 12345678 0");
        assertTrue(actual);
    }

    @Test
    void valid_command_where_cd_amount_is_minimum() {
        boolean actual = createValidator.validate("create cd 12345678 0 1000");
        assertTrue(actual);
    }

    @Test
    void valid_command_where_cd_amount_is_maximum() {
        boolean actual = createValidator.validate("create cd 12345678 0 10000");
        assertTrue(actual);
    }

    @Test
    void invalid_command_where_account_already_exists() {
        bank.addChecking(12345678, 0.01);
        boolean actual = createValidator.validate("create checking 12345678 0.01");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_create_is_missing() {
        boolean actual = createValidator.validate("checking 12345678 0.01");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_create_is_misspelled() {
        boolean actual = createValidator.validate("creat checking 12345678 0.01");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_account_type_is_missing() {
        boolean actual = createValidator.validate("create 12345678 0.01");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_account_type_is_incorrect() {
        boolean actual = createValidator.validate("create abc 12345678 0.01");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_account_type_is_not_a_string() {
        boolean actual = createValidator.validate("create 123 12345678 0.01");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_id_is_missing() {
        boolean actual = createValidator.validate("create checking 0.01");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_id_length_is_not_8() {
        boolean actual = createValidator.validate("create checking 1234567 0.01");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_id_is_not_integer() {
        boolean actual = createValidator.validate("create checking abcdefgh 0.01");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_apr_is_missing() {
        boolean actual = createValidator.validate("create checking 12345678 ");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_apr_is_not_a_number() {
        boolean actual = createValidator.validate("create checking 12345678 abc");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_apr_is_out_of_range() {
        boolean actual = createValidator.validate("create savings 12345678 10.1");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_apr_is_negative() {
        boolean actual = createValidator.validate("create savings 12345678 -1");
        assertFalse(actual);
    }

    @Test
    void invalid_create_cd_command_where_amount_is_missing() {
        boolean actual = createValidator.validate("create cd 12345678 0.01");
        assertFalse(actual);
    }

    @Test
    void invalid_create_cd_command_where_amount_is_not_an_integer() {
        boolean actual = createValidator.validate("create cd 12345678 0.01 abc");
        assertFalse(actual);
    }

    @Test
    void invalid_create_cd_command_where_amount_is_negative() {
        boolean actual = createValidator.validate("create cd 12345678 0.01 -1");
        assertFalse(actual);
    }

    @Test
    void invalid_create_cd_command_where_amount_is_out_of_range() {
        boolean actual = createValidator.validate("create cd 12345678 0.01 100000");
        assertFalse(actual);
    }

    @Test
    void invalid_command_where_an_extra_parameter_is_given() {
        boolean actual = createValidator.validate("create checking 12345678 0.01 1000");
        assertFalse(actual);
    }
}
