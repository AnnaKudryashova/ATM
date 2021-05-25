package test.startpoint;

import org.junit.BeforeClass;
import org.junit.Test;
import startpoint.CardHolder;
import startpoint.UserAccount;

public class CardHolderTest {
    private static CardHolder cardHolder;

    @BeforeClass
    public static void createNewCardHolder() {
        cardHolder = new CardHolder(new UserAccount("USD"));
    }

    @Test(timeout = 10000)
    public void run_userTransactionShouldTakeLessThanTenSeconds() {
        cardHolder.run();
    }
}