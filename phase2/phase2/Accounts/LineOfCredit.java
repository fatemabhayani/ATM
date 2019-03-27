package phase2.Accounts;

import phase2.People.User;
import phase2.Transactions.Transaction;
import java.util.Calendar;

/**
 * The Line of credit, type of Credit Account.
 */
public class LineOfCredit extends CreditCard {


    /**
     * Instantiates a new Line of credit account.
     *
     * @param date         the date of creation
     * @param owner1       the owner 1
     * @param currencyCode the currency code
     * @param num          the account number
     */
    public LineOfCredit(Calendar date, User owner1, String currencyCode, int num) {
        super(date, owner1, currencyCode, num);
    }

    @Override
    public void subtract(Transaction transaction) {
        if (getCreditLimit().compareTo(transaction.getAmount()) > 0){
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
