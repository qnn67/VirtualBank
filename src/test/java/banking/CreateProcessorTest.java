package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateProcessorTest {
    CommandProcessor createProcessor;
    Bank bank;

    @BeforeEach
    void SetUp() {
        bank = new Bank();
        createProcessor = new CreateProcessor(bank);
    }

    @Test
    void valid_create_command_where_checking_account_exists_in_bank() {
        createProcessor.execute("create checking 12345678 0.1");
        assertEquals(12345678, bank.getAccounts().get(12345678).getId());
        assertEquals(0.1, bank.getAccounts().get(12345678).getApr());
    }

    @Test
    void valid_create_command_where_savings_account_exists_in_bank() {
        createProcessor.execute("create savings 12345678 0.1");
        assertEquals(12345678, bank.getAccounts().get(12345678).getId());
        assertEquals(0.1, bank.getAccounts().get(12345678).getApr());
    }

    @Test
    void valid_create_command_where_cd_account_exists_in_bank() {
        createProcessor.execute("create cd 12345678 0.1 1000");
        assertEquals(12345678, bank.getAccounts().get(12345678).getId());
        assertEquals(0.1, bank.getAccounts().get(12345678).getApr());
    }
}
