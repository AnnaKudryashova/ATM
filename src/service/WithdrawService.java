package service;

import exception.FailedServiceException;
import exception.NoSufficientFundsException;
import startpoint.UserAccount;
import startpoint.AccountFactory;
import startpoint.UserInterface;

import java.util.Map;

public class WithdrawService implements UserRequest {
    /**
     * class executes the user's request on drawing out money
     *
     * @throws FailedServiceException in case service fails
     */
    @Override
    public void execute() throws FailedServiceException {
        String code = UserInterface.requestCurrencyCode();
        UserAccount userAccount = AccountFactory.getCurrencyBalance(code);
        while (true) {
            UserInterface.getMessage("Enter amount you want to withdraw");
            int amount;
            try {
                amount = Integer.parseInt(UserInterface.readRequest());
            } catch (Exception e) {
                UserInterface.getMessage("Invalid data");
                continue;
            }
            if (userAccount.isSufficient(amount)) {
                try {
                    Map<Integer, Integer> withdrawMap = userAccount.amountToWithdraw(amount);
                    for (Map.Entry<Integer, Integer> note : withdrawMap.entrySet()) {
                        UserInterface.getMessage(note.getKey() + " - " + note.getValue());
                    }
                    break;
                } catch (NoSufficientFundsException e) {
                }
            }
        }
    }
}
