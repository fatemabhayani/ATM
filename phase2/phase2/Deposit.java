package phase2;

import java.util.Calendar;

/**
 * The Deposit, type of Transaction
 */
public class Deposit extends Transaction {
    private Account moneyTo;

    /**
     * Instantiates a new Deposit.
     *
     * @param deposit  file containing information regarding the amount of deposit(s)
     * @param moneyTo Account to deposit the money to
     * @param date    the date of creation
     */
    public Deposit(String deposit, Account moneyTo, Calendar date) {
        super(deposit, date);
        this.moneyTo = moneyTo;
    }
    /**
     * Get whether the transaction is approved.
     *
     * @return true unless account is Credit account.
     */
    public boolean transactionApproved() {
        return !(moneyTo instanceof CreditCard);
    }
    /**
     * Make a deposit transaction.
     */
    public void makeTransaction() {
        moneyTo.add(this);
    }
    /**
     * Undo a transaction.
     */
    public void undoTransaction() {
        moneyTo.subtract(this);
    }

    @Override
    public String toString() {
        return "Deposit " + getAmount();
    }
}