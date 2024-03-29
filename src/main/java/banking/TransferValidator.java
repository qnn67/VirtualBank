package banking;

public class TransferValidator extends BaseValidator {
    String command_type;
    int from_account;
    int to_account;
    double amount;

    public TransferValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        command = command.trim();
        command = command.toLowerCase();
        String[] command_params = command.split(" ");

        int num_of_params = command_params.length;
        if (num_of_params != 4) {
            return false;
        } else {
            command_type = command_params[0].toLowerCase();
        }

        try {
            from_account = Integer.parseInt(command_params[1]);
            to_account = Integer.parseInt(command_params[2]);
            amount = Double.parseDouble(command_params[3]);
        } catch (NumberFormatException e) {
            return false;
        }

        if (returnTrueIfTransferIsSpelledIncorrectly()) {
            return false;
        }
        if (returnTrueIfAccountDoesNotExists()) {
            return false;
        }
        if (returnTrueIfTargetAccountIsCd()) {
            return false;
        }

        return true;
    }

    private boolean returnTrueIfTargetAccountIsCd() {
        if (bank.getAccounts().get(to_account).getType().equals("cd")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean returnTrueIfTransferIsSpelledIncorrectly() {
        if (command_type.equals("transfer")) {
            return false;
        } else {
            return true;
        }
    }

    private boolean returnTrueIfAccountDoesNotExists() {
        if (bank.account_exists(from_account) && bank.account_exists(to_account)) {
            return false;
        } else {
            return true;
        }
    }
}
