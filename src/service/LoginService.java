package service;

import exception.FailedServiceException;
import startpoint.UserInterface;

import java.util.ResourceBundle;

public class LoginService implements UserRequest {
    /**
     * class verifies user input of card number and PIN number
     */
    private final ResourceBundle rb = ResourceBundle.getBundle("resources.verifiedCards");

    @Override
    public void execute() throws FailedServiceException, InterruptedException {
        while (true) {
            UserInterface.getMessage("Enter your card number, please");
            String cardNumber = UserInterface.readRequest().trim();
            UserInterface.getMessage("Enter your PIN-code, please");
            String cardPin = UserInterface.readRequest().trim();
            if (cardNumber.length() == 9 && cardPin.length() == 4) {
                if (cardPin.equals(rb.getString(cardNumber))) {
                    UserInterface.getMessage("Verification is successful.");
                    break;
                } else {
                    UserInterface.getMessage("Wrong card number or PIN-code");
                }
            } else {
                UserInterface.getMessage("Invalid data");
            }
        }
    }
}