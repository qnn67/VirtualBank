package banking;

public class CreateProcessor extends CommandProcessor {
    private String account_type;
    private int id;
    private double apr;
    private double cd_amount;

    public CreateProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void execute(String command) {

        command = command.trim();
        command = command.toLowerCase();
        String[] command_params = command.split(" ");
        account_type = command_params[1];
        id = Integer.valueOf(command_params[2]);
        apr = Double.valueOf(command_params[3]);

        if (account_type.equals("checking")) {
            bank.addChecking(id, apr);
        }
        if (account_type.equals("savings")) {
            bank.addSavings(id, apr);
        }
        if (account_type.equals("cd")) {
            cd_amount = Double.valueOf(command_params[4]);
            bank.addCD(id, apr, cd_amount);
        }

        bank.getAccounts().get(id).addToTransactionHistory(command);

    }
}
