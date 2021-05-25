package startpoint;

import exception.FailedServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {
    /**
     * class simulates a process of interaction with a user:
     * it displays possible operations and gets user's requests
     * for terminal commands handling.
     */
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void getMessage(String message) {
        System.out.println(message);
    }

    public static String readRequest() throws FailedServiceException {
        String str = "";
        try {
            str = reader.readLine();
            if (str.equalsIgnoreCase("exit")) throw new FailedServiceException();
        } catch (IOException e) {
        }
        return str;
    }

    public static BankService requestService() {
        getMessage("Please, select a number:" +
                "\n1 - BALANCE, \n2 - DEPOSIT, \n3 - WITHDRAW, \n4 - CURRENCY, \n5 - EXIT");
        try {
            int requestOption = Integer.parseInt(readRequest());
            return BankService.getService(requestOption);
        } catch (IllegalArgumentException e) {

        } catch (Exception e) {
        }
        getMessage("Invalid data. Please, try again!");
        return requestService();
    }

    public static String requestCurrencyCode() throws FailedServiceException {
        getMessage("Enter currency code, please");
        String code = readRequest().trim();
        if (code.length() == 3) {
            code = code.toUpperCase();
            return code;
        }
        getMessage("Invalid data, please, check again");
        return requestCurrencyCode();
    }

    public static String[] requestCurrencyDetails(String currencyCode) throws FailedServiceException {
        getMessage("Enter nominal and amount, please for " + currencyCode);
        return readRequest().split("\\s");
    }

    public static String[] requestCurrencyForExchange(String currencyCode) throws FailedServiceException {
        getMessage("Enter amount of " + currencyCode + " to buy. Nominal and amount");
        return readRequest().split("\\s");
    }
}