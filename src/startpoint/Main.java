package startpoint;

import exception.*;
import service.*;

import java.util.Locale;

/**
 * Automatic Teller Machine
 *
 * @author Anna Kudryashova
 */
public class Main {
    /**
     * First off, class imitates money transactions carried out by two cardholders, -
     * namely, wife and husband. If amount balance is not sufficient, recharge of
     * the account is happened. After two synchronized threads finished,
     * ATM starts interaction with a user through UserInterface class.
     */
    public static void main(String[] args) throws InterruptedException {
        UserAccount userAccount = new UserAccount("RUB");
        CardHolder cardHolder = new CardHolder(userAccount);
        Thread thread1 = new Thread(cardHolder);
        Thread thread2 = new Thread(cardHolder);
        thread1.setName("Wife");
        thread2.setName("Husband");
        thread1.start();
        thread2.start();
        System.out.println("Balance is " + userAccount.getAmount());
        thread1.join();
        thread2.join();
        Locale.setDefault(Locale.ENGLISH);
        try {
            BankService currentBankService = BankService.LOGIN;
            UserRequestExecutor.execute(currentBankService);
            do {
                currentBankService = UserInterface.requestService();
                UserRequestExecutor.execute(currentBankService);
            } while (currentBankService != BankService.EXIT);
        } catch (FailedServiceException e) {
        }
    }
}