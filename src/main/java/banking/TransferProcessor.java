package banking;

public class TransferProcessor extends CommandProcessor {
    String command_type;
    int from_account;
    int to_account;
    Double amount;

    public TransferProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void execute(String command) {
        command = command.trim();
        command = command.toLowerCase();
        String[] command_params = command.split(" ");
        command_type = command_params[0];
        from_account = Integer.parseInt(command_params[1]);
        to_account = Integer.parseInt(command_params[2]);
        amount = Double.parseDouble(command_params[3]);

        Double actual_amount = bank.getAccounts().get(from_account).withdraw(amount);
        bank.getAccounts().get(to_account).deposit(actual_amount);

        addTransactionHistoryToBothAccounts(command);
    }

    private void addTransactionHistoryToBothAccounts(String command) {
        bank.getAccounts().get(from_account).addToTransactionHistory(command);
        bank.getAccounts().get(to_account).addToTransactionHistory(command);
    }
}
