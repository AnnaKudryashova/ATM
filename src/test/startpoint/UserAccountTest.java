package test.startpoint;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import startpoint.UserAccount;

public class UserAccountTest {
    private static UserAccount userAccount;

    @BeforeClass
    public static void createNewUserAccount() {
        userAccount = new UserAccount("USD");
    }

    @Test
    public void getAmount_initialAmountShouldBeZero() {
        Assert.assertEquals(0, userAccount.getAmount());
    }

    @Test
    public void isInBlack_shouldBeTrueForEmptyAccount() {
        Assert.assertFalse("true", userAccount.isInBlack());
    }

    @Test
    public void isSufficient_requestedAmountShouldBeNotEnough() {
        Assert.assertTrue("false", userAccount.isSufficient(-1));
    }
}