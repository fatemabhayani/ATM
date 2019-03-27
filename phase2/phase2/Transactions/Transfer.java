package phase2.Transactions;

import phase2.Accounts.*;
import phase2.ForeignCurrency;
import java.util.Calendar;

/**
 * A transfer transaction.
 */
public class Transfer extends Transaction {

    /**
     * The account receiving the transfer.
     */
    private Account moneyTo;

    /**
     * The account transferring money.
     */
    private Account moneyFrom;

    /**
     * Instantiates a new Transfer.
     *
     * @param amount    the amount to transfer
     * @param moneyTo   the account the money should be transferred to
     * @param moneyFrom the account where the money is from
     * @param date      the date of creation
     */
    public Transfer(ForeignCurrency amount, Account moneyTo, Account moneyFrom, Calendar date) {
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
        if (moneyFrom.getClass().isInstance(CreditCard.class)) {
            return false;
        } else if (moneyFrom.getClass().isInstance(LineOfCredit.class)) {
            return true;
        } else if (moneyFrom.getClass().isInstance(Savings.class)) {
            return moneyFrom.getBalance().compareTo(getAmount()) >= 0;
        } else {
            return (moneyFrom.getBalance().getAmount() >= 0 && moneyFrom.getBalance().compareTo(
            new ForeignCurrency("CAD", getAmount().convert("CAD").getAmount() - 100)
            ) >= 0);
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
     * Undoes a transfer transaction.
     */
    public void undoTransaction() {
        moneyFrom.add(this);
        moneyTo.subtract(this);
    }

    @Override
    public String toString() {
        return "Transfer " + getAmount();
    }
}
