package banking;

public class CreateValidator extends BaseValidator {
    String command_type;
    String account_type;
    int id;
    double apr;
    double cd_amount;

    public CreateValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        command = command.trim();
        command = command.toLowerCase();
        String[] command_params = command.split(" ");


        int num_of_params = command_params.length;
        if (num_of_params > 5 | num_of_params < 4) {
            return false;
        } else {
            command_type = command_params[0];
            account_type = command_params[1];
            try {
                id = Integer.valueOf(command_params[2]);
                apr = Double.valueOf(command_params[3]);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        if (num_of_params == 5) {
            if (account_type.equals("cd")) {
                try {
                    cd_amount = Double.parseDouble(command_params[4]);
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (account_type.equals("cd")) {
                return false;
            }
        }

        if (returnTrueIfCreateIsSpelledIncorrectly()) {
            return false;
        }
        if (returnTrueIfAccountExists()) {
            return false;
        }
        if (returnTrueIfIdIsNotInteger()) {
            return false;
        }
        if (returnTrueIfIdLengthIsNot8()) {
            return false;
        }
        if (returnTrueIfAccountTypeIsIncorrect()) {
            return false;
        }
        if (returnTrueIfAprIsIncorrect()) {
            return false;
        }
        if (returnTrueIfAprIsNotANumber()) {
            return false;
        }
        if (account_type.equals("cd")) {
            if (returnTrueIfCdAmountIsOutOfRange()) {
                return false;
            }
        }

        return true;
    }

    private boolean returnTrueIfCdAmountIsOutOfRange() {
        if (cd_amount < 1000 | cd_amount > 10000) {
            return true;
        } else {
            return false;
        }
    }

    private boolean returnTrueIfAprIsNotANumber() {
        try {
            Double.valueOf(apr);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private boolean returnTrueIfAccountTypeIsIncorrect() {
        if (!(account_type.equals("checking")) & !(account_type.equals("savings")) & !(account_type.equals("cd"))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean returnTrueIfIdIsNotInteger() {
        try {
            Integer.valueOf(id);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private boolean returnTrueIfCreateIsSpelledIncorrectly() {
        if (command_type.equals("create")) {
            return false;
        } else {
            return true;
        }
    }

    private boolean returnTrueIfAccountExists() {
        if (bank.account_exists(id)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean returnTrueIfAprIsIncorrect() {
        if (apr < 0 | apr > 10) {
            return true;
        } else {
            return false;
        }
    }

    private boolean returnTrueIfIdLengthIsNot8() {
        int length = String.valueOf(id).length();
        if (length != 8) {
            return true;
        } else {
            return false;
        }
    }
}
