package banking;

public class WithdrawProcessor extends CommandProcessor {
    String command_type;
    int id;
    Double amount;

    public WithdrawProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void execute(String command) {
        command = command.trim();
        command = command.toLowerCase();
        String[] command_params = command.split(" ");
        command_type = command_params[0];
        id = Integer.valueOf(command_params[1]);
        amount = Double.valueOf(command_params[2]);


        bank.getAccounts().get(id).withdraw(amount);
        bank.getAccounts().get(id).addToTransactionHistory(command);
    }
}
