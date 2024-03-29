package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositProcessorTest {
    CommandProcessor createProcessor;
    CommandProcessor depositProcessor;
    Bank bank;

    @BeforeEach
    void SetUp() {
        bank = new Bank();
        createProcessor = new CreateProcessor(bank);
        depositProcessor = new DepositProcessor(bank);
        createProcessor.execute("create checking 12345678 0.1");
    }

    @Test
    void valid_deposit_command_where_balance_of_new_account_is_updated_correctly() {
        depositProcessor.execute("Deposit 12345678 100");
        assertEquals(100, bank.getAccounts().get(12345678).getBalance());
    }

    @Test
    void valid_deposit_command_where_balance_of_existing_account_is_updated_correctly() {
        depositProcessor.execute("Deposit 12345678 100");
        depositProcessor.execute("Deposit 12345678 100");
        assertEquals(200, bank.getAccounts().get(12345678).getBalance());
    }
}
