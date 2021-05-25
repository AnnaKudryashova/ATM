package startpoint;

public enum BankService {
    /**
     * list of available services provided by ATM
     * Login - to verify user input of card number and PIN number
     * Balance - to check current rest of money on the account
     * Deposit - to refill balance
     * Withdraw - to draw out some money
     * Currency - to exchange rubles for currency
     * Exit - to quit menu
     */
    LOGIN,
    BALANCE,
    DEPOSIT,
    WITHDRAW,
    CURRENCY,
    EXIT;

    public static BankService getService(int i) {
        switch (i) {
            case 1:
                return BankService.BALANCE;
            case 2:
                return BankService.DEPOSIT;
            case 3:
                return BankService.WITHDRAW;
            case 4:
                return BankService.CURRENCY;
            case 5:
                return BankService.EXIT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
