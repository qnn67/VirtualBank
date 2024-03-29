package banking;

public abstract class CommandProcessor {
    protected Bank bank;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    abstract public void execute(String s);
}
