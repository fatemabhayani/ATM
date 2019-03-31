package phase2.Transactions;

import phase2.Accounts.*;
import ForeignCurrency;
import java.util.Calendar;

/**
 * A bill transaction.
 */
public class Bill extends Transaction {

    /**
     * The account paying the bill.
     */
    private Account moneyFrom;

    /**
     * Instantiates a new bill.
     *
     * @param amount    the amount payed
     * @param moneyFrom the account the money originates from
     * @param date      date of transaction
     */
    public Bill(ForeignCurrency amount, Account moneyFrom, Calendar date) {
        super(amount, date);
        this.moneyFrom = moneyFrom;
    }

    /**
     * Returns whether or not the bill payment is approved.
     *
     * @return true iff the transaction is approved
     */
    public boolean transactionApproved() {
        if (moneyFrom.getClass().isInstance(CreditCard.class)) {
            return false;
        } else if (moneyFrom.getClass().isInstance(LineOfCredit.class)) {
            return true;
        } else if (moneyFrom.getClass().isInstance(Savings.class)) {
            return moneyFrom.getBalance().compareTo(getAmount()) > 0;
        } else {
            return moneyFrom.getBalance().compareTo(getAmount()) >= 0 &&
                    moneyFrom.getBalance().compareTo(new ForeignCurrency("CAD", getAmount().
                            convert("CAD").getAmount() - 100)) >= 0;
        }
    }

    /**
     * Makes the transaction.
     */
    public void makeTransaction() {
        // Subtracts amount from moneyFrom balance and writes to outgoing.txt
        moneyFrom.subtract(amount);
    }

    /**
     * Gets the account that has made this transaction.
     *
     * @return the account making the transaction
     */
    public Account getTransactionAccount() { return moneyFrom; }

    /**
     * Undoes the transaction. Bills can't be undone, so this does nothing.
     */
    public void undoTransaction() {
        System.out.println("Bill payments cannot be undone!");
    }

    @Override
    public String toString() {
        return "B " + amount.toString() + " " + getTransactionTimestamp() + " " + moneyFrom.getAccountNum();
    }
}

