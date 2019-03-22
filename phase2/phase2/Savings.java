package phase2;

import java.util.Calendar;

/**
 * The Savings account, type of Asset Account
 */
public class Savings extends AssetAccount {

    /**
     * Instantiates a new Savings account.
     *
     * @param date the date of creation
     */
    public Savings(Calendar date) {
        super(date);
    }

    /**
     * Increase balance by the interest rate of 1%.
     */
    public void increase() {
        balance = balance * 1.001;
    }

    @Override
    public void subtract(Transaction transaction) {
        double amount = transaction.getAmount();
        if (balance - amount < 0) {
            System.out.println("Cannot have a balance below 0!");
        } else {
            balance -= amount;
            System.out.println("Transaction successful!");
        }
        transactions.add(0, transaction);
    }

    public void subtract(double amount) {
        if (balance - amount < 0) {
            System.out.println("Cannot have a balance below 0!");
        } else {
            balance -= amount;
            helpWrite(amount);
            System.out.println("Transaction successful!");
        }
    }

}

