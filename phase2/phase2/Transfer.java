package phase2;

import java.util.Calendar;

/**
 * The Transfer, type of Transaction.
 */
public class Transfer extends Transaction {

    private Account moneyTo;
    private Account moneyFrom;

    /**
     * Instantiates a new Transfer.
     *
     * @param amount    the amount to transfer
     * @param moneyTo   the account the money should be transferred to
     * @param moneyFrom the account where the money is from
     * @param date      the date of creation
     */
    Transfer(double amount, Account moneyTo, Account moneyFrom, Calendar date){
        super(amount, date);
        this.moneyTo = moneyTo;
        this.moneyFrom = moneyFrom;
    }

    public boolean transactionApproved() {
        if (moneyFrom.getClass().isInstance(CreditCard.class)) {
            return false;
        } else if (moneyFrom.getClass().isInstance(LineOfCredit.class)) {
            return true;
        } else if (moneyFrom.getClass().isInstance(Savings.class)) {
            return moneyFrom.getBalance() >= getAmount();
        } else {
            return (moneyFrom.getBalance() >= 0 && moneyFrom.getBalance() - getAmount() >= -100);
        }
    }

    public void makeTransaction() {
        moneyFrom.subtract(this);
        moneyTo.add(this);
    }

    public void undoTransaction() {
        moneyFrom.add(this);
        moneyTo.subtract(this);
    }

    @Override
    public String toString() {
        return "Transfer " + getAmount();
    }
}