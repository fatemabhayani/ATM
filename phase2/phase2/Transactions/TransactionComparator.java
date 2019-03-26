package phase2.Transactions;

import java.util.Calendar;
import java.util.Comparator;

public class TransactionComparator implements Comparator<Transaction> {

    public int compare(Transaction t1, Transaction t2) {
        Calendar time1 = t1.getTimeOfTransaction();
        Calendar time2 = t2.getTimeOfTransaction();
        return time1.compareTo(time2);
    }
}
