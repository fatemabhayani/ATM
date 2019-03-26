package phase2;

import java.util.Calendar;
import java.util.Locale;

/**
 * The Line of credit, type of Credit Account.
 */
public class LineOfCredit extends CreditCard {

    /**
     * Instantiates a new Line of credit account.
     *
     * @param date the date of creation
     */
    LineOfCredit(Calendar date, String currencyCode) {
        super(date, currencyCode);
    }

    @Override
    public void subtract(Transaction transaction) {
        if (getCreditLimit().compareTo(transaction.getAmount()) == 1){
            getBalance().add(transaction.getAmount());
            setBalance(getBalance());
            transactions.add(transaction);
            decreaseCreditLimit(transaction.getAmount());
            System.out.println("Transaction successful!");
        } else {
            System.out.println("There isn't enough credit limit on your account to complete this transaction");
        }
    }
}
