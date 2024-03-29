package banking;

public class WithdrawValidator extends BaseValidator {
    String command_type;
    int id;
    double amount;

    public WithdrawValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        command = command.trim();
        command = command.toLowerCase();
        String[] command_params = command.split(" ");


        int num_of_params = command_params.length;
        if (num_of_params != 3) {
            return false;
        } else {
            command_type = command_params[0].toLowerCase();
        }

        try {
            id = Integer.parseInt(command_params[1]);
            amount = Double.parseDouble(command_params[2]);
        } catch (NumberFormatException e) {
            return false;
        }


        if (returnTrueIfWithdrawIsSpelledIncorrectly()) {
            return false;
        }
        if (returnTrueIfIdLengthIsNot8()) {
            return false;
        }
        if (returnTrueIfIdIsNotInBank()) {
            return false;
        }
        if (returnTrueIfNotEnoughTimePassedToWithdraw()) {
            return false;
        }
        if (returnTrueIfAmountIsNotFullWithdrawFromCDAccount()) {
            return false;
        }

        return true;
    }


    private boolean returnTrueIfAmountIsNotFullWithdrawFromCDAccount() {
        if (bank.getAccounts().get(id).getType().equals("cd") &&
                amount < bank.getAccounts().get(id).getBalance()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean returnTrueIfNotEnoughTimePassedToWithdraw() {
        if (bank.getAccounts().get(id).isAccountWithdrawable()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean returnTrueIfIdLengthIsNot8() {
        int length = String.valueOf(id).length();
        if (length == 8) {
            return false;
        } else {
            return true;
        }
    }

    private boolean returnTrueIfWithdrawIsSpelledIncorrectly() {
        if (command_type.equals("withdraw")) {
            return false;
        } else {
            return true;
        }
    }

    private boolean returnTrueIfIdIsNotInBank() {
        if (bank.account_exists(id)) {
            return false;
        } else {
            return true;
        }
    }
}
