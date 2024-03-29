package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandStorageTest {
    CommandStorage commandStorage;
    String command;

    @BeforeEach
    void SetUp() {
        commandStorage = new CommandStorage();
    }

    @Test
    void store_a_command() {
        command = "creat checking 12345678 0.2";
        commandStorage.addInvalidCommand(command);
        assertEquals(command, commandStorage.getInvalidCommands().get(0));
    }

    @Test
    void store_multiple_commands() {
        command = "creat checking 12345678 0.2";
        String command2 = "create savin 12345678 0.2";
        commandStorage.addInvalidCommand(command);
        commandStorage.addInvalidCommand(command2);
        assertTrue(commandStorage.getInvalidCommands().contains(command));
        assertTrue(commandStorage.getInvalidCommands().contains(command2));
    }

    @Test
    void valid_case_where_all_command_gets_stored() {
        command = "creat checking 12345678 0.2";
        String command2 = "create savin 12345678 0.2";
        ArrayList<String> commandList = new ArrayList<String>();
        commandList.add(command);
        commandList.add(command2);
        commandStorage.addInvalidCommand(command);
        commandStorage.addInvalidCommand(command2);
        assertEquals(commandList, commandStorage.getInvalidCommands());
    }
}
