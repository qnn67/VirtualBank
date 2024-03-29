package banking;

public class DepositValidator extends BaseValidator {

    public DepositValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        command = command.trim();
        command = command.toLowerCase();
        String[] command_params = command.split(" ");
        String command_type;
        String account_id;
        String deposit_amount;
        int id;
        double amount;

        int num_of_params = command_params.length;
        if (num_of_params != 3) {
            return false;
        } else {
            command_type = command_params[0].toLowerCase();
            account_id = command_params[1];
            deposit_amount = command_params[2];
        }

        try {
            id = Integer.parseInt(account_id);
            amount = Double.parseDouble(deposit_amount);
        } catch (NumberFormatException e) {
            return false;
        }


        if (returnTrueIfDepositIsSpelledIncorrectly(command_type)) {
            return false;
        }
        if (returnTrueIfIdLengthIsNot8(account_id)) {
            return false;
        }
        if (!(bank.account_exists(id))) {
            return false;
        }
        if (!(bank.getAccounts().get(id).isAmountValidForDeposit(amount))) {
            return false;
        }

        return true;
    }

    private boolean returnTrueIfDepositIsSpelledIncorrectly(String command_type) {
        if (command_type.equals("deposit")) {
            return false;
        } else {
            return true;
        }
    }

    private boolean returnTrueIfIdLengthIsNot8(String id) {
        if (id.length() != 8) {
            return true;
        } else {
            return false;
        }
    }


}
