package banking;

public class PassTimeValidator extends BaseValidator {
    String command_type;
    int num_of_months;

    public PassTimeValidator(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        command = command.trim();
        command = command.toLowerCase();
        String[] command_params = command.split(" ");

        int num_of_params = command_params.length;
        if (num_of_params != 2) {
            return false;
        } else {
            command_type = command_params[0].toLowerCase();
        }

        try {
            num_of_months = Integer.parseInt(command_params[1]);
        } catch (NumberFormatException e) {
            return false;
        }

        if (returnTrueIfPassIsSpelledIncorrectly()) {
            return false;
        }
        if (returnTrueIfNumOfMonthsExceeds60()) {
            return false;
        }
        if (returnTrueIfNumOfMonthsIsZeroOrLess()) {
            return false;
        }
        if (returnTrueIfNumOfMonthsIsZeroOrLess()) {
            return false;
        }


        return true;
    }


    private boolean returnTrueIfNumOfMonthsIsZeroOrLess() {
        if (num_of_months <= 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean returnTrueIfNumOfMonthsExceeds60() {
        if (num_of_months > 60) {
            return true;
        } else {
            return false;
        }
    }

    private boolean returnTrueIfPassIsSpelledIncorrectly() {
        if (command_type.equals("pass")) {
            return false;
        } else {
            return true;
        }
    }
}
