package service;

import exception.FailedServiceException;
import exception.NoSufficientFundsException;
import startpoint.*;

import java.util.Map;
import java.util.ResourceBundle;

public class CurrencyService implements UserRequest {
    private ResourceBundle rb = ResourceBundle.getBundle("resources.currency");

    @Override
    public void execute() throws FailedServiceException, InterruptedException {
        final int rateEUR = getRate(88, 92);
        final int rateUSD = getRate(74, 77);
        UserInterface.getMessage(String.format(rb.getString("info.format"), rateUSD, rateEUR));
        String currencyCode = UserInterface.requestCurrencyCode();
        UserAccount userCurrencyAccount = AccountFactory.getCurrencyBalance(currencyCode);
        UserAccount userLocalAccount = AccountFactory.getCurrencyBalance("RUB");
        int convertSum;
        int note;
        int count;
        while (true) {
            try {
                String[] amountToChange = UserInterface.requestCurrencyForExchange(currencyCode);
                note = Integer.parseInt(amountToChange[0]);
                count = Integer.parseInt(amountToChange[1]);
                if (currencyCode.equals("USD")) {
                    convertSum = rateUSD * note * count;
                } else {
                    convertSum = rateEUR * note * count;
                }
            } catch (Exception e) {
                UserInterface.getMessage("Invalid data");
                break;
            }
            if (userLocalAccount.isSufficient(convertSum)) {
                try {
                    Map<Integer, Integer> withdrawMap = userLocalAccount.amountToWithdraw(convertSum);
                    for (Map.Entry<Integer, Integer> pair : withdrawMap.entrySet()) {
                        UserInterface.getMessage(pair.getKey() + " - " + pair.getValue());
                        userCurrencyAccount.addAmount(note, count);
                        UserInterface.getMessage(String.format(rb.getString("success.format"), (note * count), currencyCode));
                    }
                    break;
                } catch (NoSufficientFundsException e) {
                    e.printStackTrace();
                }
            } else {
                UserInterface.getMessage(rb.getString("failure"));
                break;
            }
        }
    }

    private int getRate(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
