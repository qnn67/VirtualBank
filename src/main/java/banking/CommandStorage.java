package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {

    private List<String> storage = new ArrayList<>();

    public void addInvalidCommand(String command) {
        storage.add(command);
    }

    public List<String> getInvalidCommands() {
        return storage;
    }
}
