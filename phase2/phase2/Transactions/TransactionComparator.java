package phase2.Transactions;

import java.util.Calendar;
import java.util.Comparator;

/**
 * The type Transaction comparator.
 */
public class TransactionComparator implements Comparator<Transaction> {

    /**
     * Compares the date of two transactions.
     *
     * @param t1 the first transaction
     * @param t2 the second transaction
     * @return a negative value if t1 was made earlier, a positive value if t1 was made later,
     * and 0 if t1 and t2 were made at the same date
     */
    public int compare(Transaction t1, Transaction t2) {
        Calendar time1 = t1.getTimeOfTransaction();
        Calendar time2 = t2.getTimeOfTransaction();
        return time1.compareTo(time2);
    }
}
