package banking;

public class PassTimeProcessor extends CommandProcessor {
    private String num_of_months;

    public PassTimeProcessor(Bank bank) {
        super(bank);
    }

    @Override
    public void execute(String command) {
        command = command.trim();
        command = command.toLowerCase();
        String[] command_params = command.split(" ");
        num_of_months = command_params[1];
        Integer months = Integer.parseInt(num_of_months);

        double balance;
        for (Integer id : bank.getAccounts().keySet()) {
            for (int i = 0; i < months; i++) {
                balance = bank.getAccounts().get(id).getBalance();

                if (balance == 0) {
                    bank.removeAccount(id);
                    continue;
                }

                if (balance < 100) {
                    fineAccountForLowBalance(id);
                }

                calculateApr(id);
                bank.getAccounts().get(id).addTime(1);
            }
        }
    }

    private void calculateApr(Integer id) {
        Double apr = bank.getAccounts().get(id).getApr();
        Double balance = bank.getAccounts().get(id).getBalance();
        Double amount;
        Double monthly_rate = apr / 100 / 12;

        amount = balance * monthly_rate;

        if (bank.getAccounts().get(id).getType().equals("CD")) {
            for (int i = 0; i < 3; i++) {
                bank.getAccounts().get(id).deposit(amount);
            }
        } else {
            bank.getAccounts().get(id).deposit(amount);
        }
    }

    private void fineAccountForLowBalance(Integer id) {
        bank.getAccounts().get(id).withdraw(25);
    }


}
