package service;

import exception.FailedServiceException;
import startpoint.UserAccount;
import startpoint.AccountFactory;
import startpoint.UserInterface;

import java.util.ResourceBundle;

public class DepositService implements UserRequest {
    /**
     * class executes the user's request on balance refill
     */
    private ResourceBundle rb = ResourceBundle.getBundle("resources.deposit");

    @Override
    public void execute() throws FailedServiceException {
        UserInterface.getMessage(rb.getString("before"));
        String code = UserInterface.requestCurrencyCode();
        while (true) {
            try {
                String[] values = UserInterface.requestCurrencyDetails(code);
                UserAccount userAccount = AccountFactory.getCurrencyBalance(code);
                int note = Integer.parseInt(values[0]);
                int count = Integer.parseInt(values[1]);
                userAccount.addAmount(note, count);
                UserInterface.getMessage(String.format(rb.getString("success.format"), (note * count), code));
                break;
            } catch (NumberFormatException e) {
                UserInterface.getMessage(rb.getString("invalid.data"));
            }
        }
    }
}