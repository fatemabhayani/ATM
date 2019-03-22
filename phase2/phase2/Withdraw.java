package phase2;

import java.util.Calendar;

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
    Withdraw(double amount, Account moneyFrom, Calendar date){
        super(amount, date);
        this.moneyFrom = moneyFrom;
    }

    public boolean transactionApproved() {
        boolean isEnoughBills = (getAmount() > ATM.c.totalBalance());
        boolean isValidAmount = (getAmount() % 5 == 0);

        boolean isEnoughFunds = true;
        if (moneyFrom.getClass().isInstance(Savings.class)) {
            isEnoughFunds = (moneyFrom.getBalance() >= getAmount());
        } else if (moneyFrom.getClass().isInstance(Chequing.class)) {
            isEnoughFunds = (moneyFrom.getBalance() >= 0 && moneyFrom.getBalance() - getAmount() >= -100);
        }

        return (isEnoughBills && isValidAmount && isEnoughFunds);
    }

    public void makeTransaction() {
        moneyFrom.subtract(this);
        ATM.c.withdrawBills(getAmount());
    }

    public void undoTransaction() {
        moneyFrom.add(this);
    }

    @Override
    public String toString() {
        return "Withdraw " + getAmount();
    }
}

