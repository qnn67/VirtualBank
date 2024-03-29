package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckingTest {
    public static final int id = 12345678;
    public static final double apr = 0.2;
    public int amount = 10;
    Checking checking;

    @BeforeEach
    void setUp() {
        checking = new Checking(this.id, this.apr);
    }

    @Test
    void balance_is_zero_when_account_is_created() {
        assertEquals(checking.getBalance(), 0);
    }

    @Test
    void depositing_zero_works() {
        checking.deposit(0);
        assertEquals(checking.getBalance(), 0);
    }

    @Test
    void deposit_amount_is_correct() {
        checking.deposit(this.amount);
        assertEquals(checking.getBalance(), this.amount);
    }

    @Test
    void deposit_maximum_mount_is_correct() {
        checking.deposit(1000);
        assertEquals(checking.getBalance(), 1000);
    }

    @Test
    void depositing_twice_works() {
        checking.deposit(this.amount);
        checking.deposit(this.amount);
        assertEquals(checking.getBalance(), this.amount * 2);
    }

    @Test
    void withdrawal_works() {
        checking.deposit(this.amount);
        checking.withdraw(this.amount);
        assertTrue(checking.getBalance() == 0);
    }

    @Test
    void withdrawal_works_with_any_valid_amount() {
        checking.deposit(this.amount);
        checking.withdraw(this.amount - 5);
        assertTrue(checking.getBalance() == 5);
    }

    @Test
    void withdrawing_twice_works() {
        checking.deposit(this.amount);
        checking.withdraw(this.amount / 2);
        checking.withdraw(this.amount / 2);
        assertEquals(checking.getBalance(), 0);
    }

    @Test
    void check_balance_when_withdrawing_more_than_balance() {
        checking.deposit(this.amount);
        checking.withdraw(100);
        assertTrue(checking.getBalance() >= 0);
    }

}
