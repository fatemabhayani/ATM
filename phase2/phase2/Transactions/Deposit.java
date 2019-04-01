package phase2.Transactions;

import phase2.Accounts.*;
import phase2.Tradable.*;

import java.util.Calendar;

/**
 * A deposit transaction.
 */
public class Deposit extends Transaction {

    /**
     * The account making the deposit.
     */
    private final Account moneyTo;

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
     * Instantiates a new Deposit.
     *
     * @param amount  the money being deposited
     * @param moneyTo account to deposit the money to
     * @param date    the date of creation
     */
    public Deposit(ForeignCurrency amount, Account moneyTo, Calendar date) {
        super(amount, date);
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
     * Gets the account that has made this transaction.
     *
     * @return the account making the transaction
     */
    public Account getTransactionAccount() { return moneyTo; }

    /**
     * Undoes a deposit transaction.
     */
    public void undoTransaction() {
        moneyTo.subtract(this);
    }

    public String summary() {
        return "Deposit: " + amount.toString() + " at " + getTransactionTimestamp() + " to account number " +
                moneyTo.getAccountNum();
    }

    @Override
    public String toString() {
        return "D " + amount.toString() + " " + getTransactionTimestamp() + " " + moneyTo.getAccountNum();
    }
}