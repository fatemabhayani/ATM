package phase2.Transactions;

import phase2.Accounts.*;
import phase2.Tradable.*;
import java.util.Calendar;

/**
 * A transfer transaction.
 */
public class Transfer extends Transaction {

    /**
     * The account receiving the transfer.
     */
    private final Account moneyTo;

    /**
     * The account transferring money.
     */
    private final Account moneyFrom;

    /**
     * Instantiates a new Transfer.
     *
     * @param amount    the amount to transfer
     * @param moneyTo   the account the money should be transferred to
     * @param moneyFrom the account where the money is from
     * @param date      the date of creation
     */
    public Transfer(ForeignCurrency amount, Account moneyFrom, Account moneyTo, Calendar date) {
        super(amount, date);
        this.moneyTo = moneyTo;
        this.moneyFrom = moneyFrom;
    }

    /**
     * Gets whether the transaction is approved.
     *
     * @return true unless account is credit card account.
     */
    public boolean transactionApproved() {
        if (moneyFrom instanceof LineOfCredit) {
            return true;
        } else if (moneyFrom instanceof CreditCard) {
            return false;
        } else if (moneyFrom instanceof Savings) {
            return moneyFrom.getBalance().compareTo(getAmount()) >= 0;
        } else {
            return (moneyFrom.getBalance().getAmount() >= 0 && moneyFrom.getBalance().compareTo(
            new ForeignCurrency("CAD", getAmount().convert("CAD").getAmount() - 100)) >= 0);
        }
    }

    /**
     * Makes a transfer transaction.
     */
    public void makeTransaction() {
        moneyFrom.subtract(this);
        moneyTo.add(this);
    }

    /**
     * Gets the account that has made this transaction.
     *
     * @return the account making the transaction
     */
    public Account getTransactionAccount() { return moneyFrom; }

    /**
     * Undoes a transfer transaction.
     */
    public void undoTransaction() {
        moneyFrom.add(this);
        moneyTo.subtract(this);
    }

    public String summary() {
        return "Transfer: " + amount.toString() + " at " + getTransactionTimestamp() + " from account number " +
                moneyFrom.getAccountNum() + " to account number " + moneyTo.getAccountNum();
    }

    @Override
    public String toString() {
        return "T " + amount.toString() + " " + getTransactionTimestamp() + " " + moneyFrom.getAccountNum() + " " +
                moneyTo.getAccountNum();
    }
}
