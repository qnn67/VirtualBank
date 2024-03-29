package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsTest {
    public static final int id = 12345678;
    public static double apr = 0.2;
    Savings savings;

    @BeforeEach
    void setUp() {
        savings = new Savings(this.id, this.apr);
    }

    @Test
    void check_if_balance_is_zero_when_account_is_created() {
        assertEquals(savings.getBalance(), 0);
    }
}
