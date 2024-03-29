package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterControlTest {
    MasterControl masterControl;
    List<String> input;

    private void assertSingleCommand(String command, List<String> actual) {
        assertEquals(1, actual.size());
        assertEquals(command, actual.get(0));
    }

    @BeforeEach
    void SetUp() {
        input = new ArrayList<>();
        Bank bank = new Bank();
        masterControl = new MasterControl(bank, new CreateValidator(bank),
                new DepositValidator(bank), new WithdrawValidator(bank),
                new TransferValidator(bank), new PassTimeValidator(bank),
                new CreateProcessor(bank), new DepositProcessor(bank),
                new WithdrawProcessor(bank), new TransferProcessor(bank),
                new PassTimeProcessor(bank), new CommandStorage());
    }

    @Test
    void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
        input.add("Create savings 12345678 0.6"); // valid
        input.add("Deposit 12345678 700"); //valid
        input.add("Deposit 12345678 5000"); //invalid
        input.add("creAte cHecKing 98765432 0.01"); //invalid
        input.add("Deposit 98765432 300"); //valid
        input.add("Transfer 98765432 12345678 300"); //valid
        input.add("Pass 1"); // valid - not in cmd storage
        input.add("Create cd 23456789 1.2 2000"); //valid
        List<String> actual = masterControl.start(input);

        assertEquals(5, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
        assertEquals("Deposit 12345678 5000", actual.get(4));
    }

    @Test
    void typo_in_create_command_is_invalid() {
        input.add("Creat checking 12345678 1.0");
        List<String> actual = masterControl.start(input);
        assertSingleCommand("Creat checking 12345678 1.0", actual);
    }

    @Test
    void typo_in_deposit_command_is_invalid() {
        input.add("Depositt checking 100");
        List<String> actual = masterControl.start(input);
        assertSingleCommand("Depositt checking 100", actual);
    }

    @Test
    void two_typo_commands_both_invalid() {
        input.add("Creat checking 12345678 1.0");
        input.add("Depositt checking 100");
        List<String> actual = masterControl.start(input);
        assertEquals(2, actual.size());
        assertEquals("Creat checking 12345678 1.0", actual.get(0));
        assertEquals("Depositt checking 100", actual.get(1));
    }

    @Test
    void invalid_to_create_account_with_same_ID() {
        input.add("Create checking 12345678 1.0");
        input.add("Create checking 12345678 2.0");
        List<String> actual = masterControl.start(input);
        System.out.print(actual);
        assertEquals("Checking 12345678 0.00 1.00", actual.get(0));
        assertEquals("Create checking 12345678 2.0", actual.get(1));
    }

    @Test
    void valid_to_deposit_account() {
        input.add("Deposit 12345678 100");
        List<String> actual = masterControl.start(input);
        assertSingleCommand("Deposit 12345678 100", actual);
    }


}
