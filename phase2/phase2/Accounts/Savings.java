package phase2.Accounts;

import phase2.ForeignCurrency;
import phase2.People.User;
import phase2.Transactions.Transaction;

import java.util.Calendar;

/**
 * The Savings account, type of Asset Account
 */
public class Savings extends AssetAccount {

    /**
     * Instantiates a new chequing account.
     *
     * @param date the date of creation
     * @param owner1 the owner 1
     */
    public Savings(Calendar date, User owner1, String currencyCode, int num) {
        super(date, owner1, currencyCode, num);
    }

    /**
     * Instantiates a new asset account.
     *
     * @param date   the date of creation
     * @param owner1 the owner 1
     * @param owner2 the owner 2
     */
    public Savings(Calendar date, User owner1, User owner2, String currencyCode, int num) {
        super(date, owner1, owner2, currencyCode, num);
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

