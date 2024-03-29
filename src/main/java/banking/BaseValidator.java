package banking;

public abstract class BaseValidator {
    protected Bank bank;

    public BaseValidator(Bank bank) {
        this.bank = bank;
    }

    public abstract boolean validate(String command);

}
