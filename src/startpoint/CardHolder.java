package startpoint;

import exception.NoSufficientFundsException;

import java.util.concurrent.TimeUnit;

public class CardHolder implements Runnable {
    /**
     * class for concurrency practice
     * class imitates money transactions carried out by a thread, -
     * If amount balance is not sufficient,
     * funds in the amount of 2000 RUB are added.
     */
    UserAccount userAccount;

    public CardHolder(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            if (userAccount.getAmount() <= 0) {
                UserInterface.getMessage("The limit is exceeded");
                userAccount.addAmount(100, 20);
                UserInterface.getMessage("The account was refilled with 2000 RUB");
            }
            try {
                makeWithdrawal(1000);
            } catch (NoSufficientFundsException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param amount -  requested sum of money
     * @throws NoSufficientFundsException in case of lack of cash funds
     *                                    to carry out the operation of withdrawal
     */
    public synchronized void makeWithdrawal(int amount) throws NoSufficientFundsException {
        if (userAccount.getAmount() >= amount) {
            UserInterface.getMessage(Thread.currentThread().getName() + " is going to spend " + amount + " RUB");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userAccount.amountToWithdraw(amount);
            UserInterface.getMessage(Thread.currentThread().getName() + " managed to spend " + amount + " RUB");
            UserInterface.getMessage("Balance : " + userAccount.getAmount() + " RUB");
        } else {
            UserInterface.getMessage("Not enough funds to withdraw for " + Thread.currentThread().getName() +
                    "\nBalance: " + userAccount.getAmount());
        }
    }
}