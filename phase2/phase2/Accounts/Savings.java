package phase2.Accounts;

import phase2.ForeignCurrency;
import phase2.Transactions.Transaction;

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
    public Savings(Calendar date, String currencyCode) {
        super(date, currencyCode);
    }

    /**
     * Increase balance by the interest rate of 1%.
     */
    public void increase() {
        balance.multiply(1.001);
    }

    @Override
    public void subtract(Transaction transaction) {
        ForeignCurrency amount = transaction.getAmount();
        if (balance.compareTo(amount) < 0) {
            System.out.println("Cannot have a balance below 0!");
        } else {
            balance.subtract(amount);
            System.out.println("Transaction successful!");
        }
        transactions.add(0, transaction);
    }

    public void subtract(ForeignCurrency amount) {
        if (balance.compareTo(amount) < 0) {
            System.out.println("Cannot have a balance below 0!");
        } else {
            balance.subtract(amount);
            helpWrite(amount);
            System.out.println("Transaction successful!");
        }
    }

}

