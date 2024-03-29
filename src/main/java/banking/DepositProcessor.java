package banking;

public class DepositProcessor extends CommandProcessor {
    private int account_id;
    private double deposit_amount;

    public DepositProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void execute(String command) {
        command = command.trim();
        command = command.toLowerCase();
        String[] command_params = command.split(" ");
        account_id = Integer.valueOf(command_params[1]);
        deposit_amount = Double.valueOf(command_params[2]);
        bank.getAccounts().get(account_id).addToTransactionHistory(command);
        depositAccount();
    }

    private void depositAccount() {
        bank.getAccounts().get(account_id).deposit(deposit_amount);
    }

}
