package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferProcessorTest {
    TransferProcessor transferProcessor;
    Bank bank;

    @BeforeEach
    void SetUp() {
        bank = new Bank();
        transferProcessor = new TransferProcessor(bank);
        bank.addChecking(12345678, 1);
        bank.addSavings(12345678 + 1, 1);
        bank.addCD(12345678 + 2, 1, 1000);
    }

    @Test
    void valid_command() {
        bank.getAccounts().get(12345678).deposit(100);
        transferProcessor.execute("transfer 12345678 12345679 100");
        assertEquals(0, bank.getAccounts().get(12345678).getBalance());
    }

    @Test
    void valid_command_when_transfer_amount_is_higher_than_account_balance() {
        bank.getAccounts().get(12345678).deposit(100);
        transferProcessor.execute("transfer 12345678 12345679 150");
        assertEquals(0, bank.getAccounts().get(12345678).getBalance());
    }
}
