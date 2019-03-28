package phase2.Transactions;

import phase2.Accounts.*;
import java.util.Calendar;

/**
 * A deposit transaction.
 */
public class Deposit extends Transaction {

    /**
     * The account making the deposit.
     */
    private Account moneyTo;

    /**
     * Instantiates a new Deposit.
     *
     * @param deposit file containing information regarding the amount of deposit(s)
     * @param moneyTo account to deposit the money to
     * @param date    the date of creation
     */
    public Deposit(String deposit, Account moneyTo, Calendar date) {
        super(deposit, date);
        this.moneyTo = moneyTo;
    }

    /**
     * Gets whether the transaction is approved.
     *
     * @return true unless account is credit card account.
     */
    public boolean transactionApproved() {
        return !(moneyTo instanceof CreditCard);
    }

    /**
     * Makes a deposit transaction.
     */
    public void makeTransaction() {
        moneyTo.add(this);
    }

    /**
     * Undoes a deposit transaction.
     */
    public void undoTransaction() {
        moneyTo.subtract(this);
    }

    @Override
    public String toString() {
        return "Deposit " + getAmount();
    }
}