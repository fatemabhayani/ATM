package phase2;

import java.util.Calendar;
import java.util.Locale;

/**
 * The Withdraw, type of transaction
 */
public class Withdraw extends Transaction {

    private Account moneyFrom;

    /**
     * Instantiates a new Withdraw.
     *
     * @param amount    the amount of withdrawal
     * @param moneyFrom the account where the withdrawal is from
     * @param date      the date of creation
     */
    Withdraw(ForeignCurrency amount, Account moneyFrom, Calendar date){
        super(amount, date);
        this.moneyFrom = moneyFrom;
    }

    public boolean transactionApproved() {
        boolean isEnoughBills = (getAmount().convert("CAD").getAmount() > ATM.c.totalBalance());
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

    public void makeTransaction() {
        moneyFrom.subtract(this);
        ATM.c.withdrawBills(getAmount().convert("CAD").getAmount());
    }

    public void undoTransaction() {
        moneyFrom.add(this);
    }

    @Override
    public String toString() {
        return "Withdraw " + getAmount();
    }
}

