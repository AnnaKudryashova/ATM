package startpoint;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AccountFactory {
    /**
     * class implements Factory pattern,
     * it creates accounts for every currency
     */
    private static Map<String, UserAccount> currencyMap = new HashMap<>();

    private AccountFactory() {
    }

    public static UserAccount getCurrencyBalance(String currencyCode) {
        if (!currencyMap.containsKey(currencyCode)) {
            currencyMap.put(currencyCode, new UserAccount(currencyCode));
        }
        return currencyMap.get(currencyCode);
    }

    public static Collection<UserAccount> getAllCurrencyBalance() {
        return currencyMap.values();
    }
}
