package phase2;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * The Line of credit, type of Credit Account.
 */
public class LineOfCredit extends CreditCard {

    /**
     * Instantiates a new Line of credit account.
     *
     * @param date the date of creation
     */
    LineOfCredit(Calendar date, ArrayList<Transaction> transactions) {
        super(date, transactions);
    }

    LineOfCredit(Calendar date) {
        super(date);
    }

    @Override
    public void subtract(Transaction transaction) {
        setBalance(getBalance() + transaction.getAmount());
        transactions.add(transaction);
        System.out.println("Transaction successful!");
    }

}
