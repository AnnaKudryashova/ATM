package service;

import startpoint.UserAccount;
import startpoint.AccountFactory;
import startpoint.UserInterface;

import java.util.ResourceBundle;

public class BalanceService implements UserRequest {
    /**
     * class checks rest of money on the account
     * it displays the current state of balance.
     */
    private ResourceBundle rb = ResourceBundle.getBundle("resources.balance");

    @Override
    public void execute() {
        UserInterface.getMessage(rb.getString("before"));
        if (AccountFactory.getAllCurrencyBalance().size() == 0) {
            UserInterface.getMessage(rb.getString("no.funds"));
        } else {
            int count = 0;
            for (UserAccount note : AccountFactory.getAllCurrencyBalance()) {
                if (note.isInBlack()) {
                    UserInterface.getMessage(note.getCurrencyCode() + " " + note.getAmount());
                } else {
                    count++;
                }
                if (count == AccountFactory.getAllCurrencyBalance().size()) {
                    UserInterface.getMessage(rb.getString("no.funds"));
                }
            }
        }
    }
}