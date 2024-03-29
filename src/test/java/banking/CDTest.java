package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CDTest {
    public static final int id = 12345678;
    public static double apr = 0.2;
    public int amount = 1000;
    Account cd;

    @BeforeEach
    void setUp() {
        cd = new CD(this.id, this.apr, this.amount);
    }

    @Test
    void balance_is_correct_when_account_is_created() {
        assertEquals(amount, cd.getBalance());
    }

}
