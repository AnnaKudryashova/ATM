package service;

import exception.FailedServiceException;
import startpoint.BankService;

import java.util.HashMap;
import java.util.Map;

public class UserRequestExecutor {
    /**
     * helper class that deals with all requests making new
     * objects for operation services
     */
    private static Map<BankService, UserRequest> requestMap = new HashMap<>();

    static {
        requestMap.put(BankService.LOGIN, new LoginService());
        requestMap.put(BankService.BALANCE, new BalanceService());
        requestMap.put(BankService.DEPOSIT, new DepositService());
        requestMap.put(BankService.WITHDRAW, new WithdrawService());
        requestMap.put(BankService.CURRENCY, new CurrencyService());
        requestMap.put(BankService.EXIT, new ExitService());
    }

    private UserRequestExecutor() {
    }

    public static final void execute(BankService bankService) throws FailedServiceException, InterruptedException {
        requestMap.get(bankService).execute();
    }
}