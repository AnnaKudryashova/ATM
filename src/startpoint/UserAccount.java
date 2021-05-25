package startpoint;

import exception.NoSufficientFundsException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserAccount {
    /**
     * class holds all info about a user's account
     * String currencyCode - currency type, e.g.USD/RUB/EUR
     * Map<Integer, Integer> notes -
     * currency denomination and quantity of banknotes
     * money flow executes through methods addAmount and getAmount
     */
    private String currencyCode;
    private Map<Integer, Integer> notes = new HashMap<>();
    Lock lock = new ReentrantLock();

    public UserAccount(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int note, int count) {
        lock.lock();
        notes.put(note, count);
        lock.unlock();
    }

    public synchronized int getAmount() {
        int sum = 0;
        for (Map.Entry<Integer, Integer> note : notes.entrySet()) {
            sum += note.getKey() * note.getValue();
        }
        return sum;
    }

    /**
     * @return true if balance is positive
     */
    public synchronized boolean isInBlack() {
        return !notes.isEmpty();
    }

    /**
     * @param amount - requested sum of money
     * @return true if total balance amount is
     * more than cash required amount
     */
    public synchronized boolean isSufficient(int amount) {
        return getAmount() >= amount;
    }

    /**
     * @param amount - requested sum of money
     * @return set of currency denomination and quantity of banknotes
     * @throws NoSufficientFundsException in case of lack of cash funds
     *                                    to carry out the operation of withdrawal
     */
    public Map<Integer, Integer> amountToWithdraw(int amount) throws NoSufficientFundsException {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>();
        sortedMap.putAll(notes);
        HashMap<Integer, Integer> resultMap = new HashMap<>();
        for (int i : sortedMap.descendingKeySet()) {
            int note = i;
            if (note <= amount) {
                int count = amount / note;
                amount -= note * count;
                resultMap.put(note, count);
                if (amount == 0) {
                    break;
                }
                if (amount < 0) throw new NoSufficientFundsException();
            }
        }
        if (amount > 0) throw new NoSufficientFundsException();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Map.Entry<Integer, Integer> note : notes.entrySet()) {
            Integer key = note.getKey();
            Integer value = note.getValue();
            if (resultMap.containsKey(key)) {
                if (value - resultMap.get(key) != 0)
                    map.put(key, value - resultMap.get(key));
            } else {
                map.put(key, value);
            }
        }
        notes = map;
        return resultMap;
    }
}