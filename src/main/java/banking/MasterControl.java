package banking;

import java.util.ArrayList;
import java.util.List;

public class MasterControl {
    private Bank bank;
    private CreateValidator createValidator;
    private DepositValidator depositValidator;
    private WithdrawValidator withdrawValidator;
    private TransferValidator transferValidator;
    private PassTimeValidator passTimeValidator;
    private CreateProcessor createProcessor;
    private DepositProcessor depositProcessor;
    private WithdrawProcessor withdrawProcessor;
    private TransferProcessor transferProcessor;
    private PassTimeProcessor passTimeProcessor;
    private CommandStorage commandStorage;

    private List<String> output;
    private List<String> account_creation_order = new ArrayList<>();

    public MasterControl(Bank bank, CreateValidator createValidator, DepositValidator depositValidator,
                         WithdrawValidator withdrawValidator, TransferValidator transferValidator,
                         PassTimeValidator passTimeValidator, CreateProcessor createCommandProcessor,
                         DepositProcessor depositProcessor, WithdrawProcessor withdrawProcessor,
                         TransferProcessor transferProcessor, PassTimeProcessor passTimeProcessor,
                         CommandStorage commandStorage) {
        this.bank = bank;
        this.createValidator = createValidator;
        this.depositValidator = depositValidator;
        this.withdrawValidator = withdrawValidator;
        this.transferValidator = transferValidator;
        this.passTimeValidator = passTimeValidator;
        this.createProcessor = createCommandProcessor;
        this.depositProcessor = depositProcessor;
        this.withdrawProcessor = withdrawProcessor;
        this.transferProcessor = transferProcessor;
        this.passTimeProcessor = passTimeProcessor;
        this.commandStorage = commandStorage;
        this.output = new ArrayList<>();
    }

    public List<String> start(List<String> input) {
        for (String command : input) {
            command = command.trim();
            command = command.toLowerCase();
            String[] command_params = command.split(" ");
            if (command_params[0].equals("create")) {
                handleCreate(command);
            } else if (command_params[0].equals("deposit")) {
                handleDeposit(command);
            } else if (command_params[0].equals("withdraw")) {
                handleWithdraw(command);
            } else if (command_params[0].equals("transfer")) {
                handleTransfer(command);
            } else if (command_params[0].equals("pass")) {
                handlePassTime(command);
            } else {
                commandStorage.addInvalidCommand(command);
            }
        }

        for (String account_id : account_creation_order) {
            int id = Integer.parseInt(account_id);
            if (!(bank.account_exists(id))) {
                continue;
            }
            List<String> history = bank.getAccounts().get(id).getTransactionHistory();
            for (int i = 0; i < history.size(); i++) {
                String command = history.get(i);
                String[] command_params = command.split(" ");
                if (command_params[0].equalsIgnoreCase("create")) {
                    String account_type = formatAccountType(id);
                    String balance = formatBalance(id);
                    String apr = formatApr(id);
                    String output_str = account_type + " " + account_id + " " + balance + " " + apr;
                    output.add(output_str);
                } else {
                    String str = command.substring(0, 1).toUpperCase() + command.substring(1);
                    output.add(str);
                }
            }
        }

        for (String command : commandStorage.getInvalidCommands()) {
            String str = command.substring(0, 1).toUpperCase() + command.substring(1);
            output.add(str);
        }

        return output;
    }

    private void handleCreate(String command) {
        if (createValidator.validate(command)) {
            createProcessor.execute(command);
            String[] command_params = command.split(" ");
            account_creation_order.add(command_params[2]);
        } else {
            commandStorage.addInvalidCommand(command);
        }
    }

    private void handleDeposit(String command) {
        if (depositValidator.validate(command)) {
            depositProcessor.execute(command);
        } else {
            commandStorage.addInvalidCommand(command);
        }
    }

    private void handleWithdraw(String command) {
        if (withdrawValidator.validate(command)) {
            withdrawProcessor.execute(command);
        } else {
            commandStorage.addInvalidCommand(command);
        }
    }

    private void handleTransfer(String command) {
        if (transferValidator.validate(command)) {
            transferProcessor.execute(command);
        } else {
            commandStorage.addInvalidCommand(command);
        }
    }

    private void handlePassTime(String command) {
        if (passTimeValidator.validate(command)) {
            passTimeProcessor.execute(command);
        } else {
            commandStorage.addInvalidCommand(command);
        }
    }

    private String formatAccountType(int id) {
        String account_type = bank.getAccounts().get(id).getType();
        String type = "";
        if (account_type.equals("checking")) {
            type = "Checking";
        }
        if (account_type.equals("savings")) {
            type = "Savings";
        }
        if (account_type.equals("cd")) {
            type = "Cd";
        }
        return type;
    }

    private String formatBalance(int id) {
        Double balance = bank.getAccounts().get(id).getBalance();
        String balance_str = String.format("%.2f", balance);
        return balance_str;
    }

    private String formatApr(int id) {
        Double apr = bank.getAccounts().get(id).getApr();
        String apr_str = String.format("%.2f", apr);
        return apr_str;
    }
}
