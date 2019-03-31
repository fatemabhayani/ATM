package phase2.Transactions;

import phase2.CashMachine;
import phase2.Accounts.*;
import phase2.Tradable.*;
import java.util.Calendar;

/**
 * A withdrawal transaction.
 */
public class Withdraw extends Transaction {

    /**
     * The account making the withdrawal.
     */
    private Account moneyFrom;

    /**
     * Instantiates a new withdrawal.
     *
     * @param amount    the amount of withdrawal
     * @param moneyFrom the account where the withdrawal is from
     * @param date      the date of creation
     */
    public Withdraw(ForeignCurrency amount, Account moneyFrom, Calendar date) {
        super(amount, date);
        this.moneyFrom = moneyFrom;
    }

    /**
     * Gets whether the transaction is approved.
     *
     * @return true unless account is credit card account.
     */
    public boolean transactionApproved() {
        boolean isEnoughBills = (getAmount().convert("CAD").getAmount() > CashMachine.getInstance().totalBalance());
        boolean isValidAmount = (getAmount().convert("CAD").getAmount() % 5 == 0);

        boolean isEnoughFunds = true;
        if (moneyFrom.getClass().isInstance(Savings.class)) {
            isEnoughFunds = (moneyFrom.getBalance().compareTo(getAmount()) >= 0);
        } else if (moneyFrom.getClass().isInstance(Chequing.class)) {
            isEnoughFunds = (moneyFrom.getBalance().getAmount() >= 0 && moneyFrom.getBalance().compareTo
                    (new ForeignCurrency("CAD", getAmount().convert("CAD").getAmount() -100))
                    < 0);
        }
        return (isEnoughBills && isValidAmount && isEnoughFunds);
    }

    /**
     * Makes a withdraw transaction.
     */
    public void makeTransaction() {
        moneyFrom.subtract(this);
        CashMachine.getInstance().withdrawBills(getAmount().convert("CAD").getAmount());
    }

    /**
     * Gets the account that has made this transaction.
     *
     * @return the account making the transaction
     */
    public Account getTransactionAccount() { return moneyFrom; }

    /**
     * Undoes a withdraw transaction.
     */
    public void undoTransaction() {
        moneyFrom.add(this);
    }

    @Override
    public String toString() {
        return "W " + amount.toString() + " " + getTransactionTimestamp() + " " + moneyFrom.getAccountNum();
    }
}

