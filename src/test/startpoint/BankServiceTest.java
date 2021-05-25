package test.startpoint;

import org.junit.Assert;
import org.junit.Test;
import startpoint.BankService;

public class BankServiceTest {
    @Test
    public void getService_shouldReturnCorrectService() {
        Assert.assertEquals(BankService.EXIT, BankService.getService(5));
    }
}
