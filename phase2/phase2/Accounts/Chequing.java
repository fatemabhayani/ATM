package phase2.Accounts;

import phase2.ForeignCurrency;
import phase2.People.User;
import phase2.Transactions.Transaction;

import java.util.Calendar;

/**
 * The Chequing Account, type of AssetAccount.
 */
public class Chequing extends AssetAccount {

    private boolean isPrimary;

    /**
     * Instantiates a new chequing account.
     *
     * @param isPrimary true if it is a primary account, false otherwise
     * @param date the date of creation
     */
    public Chequing(boolean isPrimary, Calendar date, User owner1, String currencyCode, int num) {
        super(date, owner1, currencyCode, num);
        this.isPrimary = isPrimary;
    }

    /**
     * Instantiates a new asset account.
     *
     * @param date   the date of creation
     * @param owner1 the owner 1
     * @param owner2 the owner 2
     */
    public Chequing(boolean isPrimary, Calendar date, User owner1, User owner2, String currencyCode, int num) {
        super(date, owner1, owner2, currencyCode, num);
        this.isPrimary = isPrimary;
    }

    /**
     * Gets whether the account is primary.
     *
     * @return true if primary account, false otherwise
     */
    public boolean getIsPrimary() { return this.isPrimary; }

    @Override
    public void subtract(Transaction transaction) {
        ForeignCurrency amount = transaction.getAmount();
        if (balance.getAmount() < 0) {
            System.out.println("Cannot transfer out of an account with a negative balance!");
        } else {
            if (balance.compareTo
                    (new ForeignCurrency("CAD", amount.convert("CAD").getAmount() -100))
                    < 0) {
                System.out.println("Cannot have a balance below -100!");
            } else {
                balance.subtract(amount);
                System.out.println("Transaction successful!");
            }
        }
        transactions.add(transaction);
    }

    @Override
    public void subtract(ForeignCurrency amount) {
        if (balance.getAmount() < 0) {
            System.out.println("Cannot transfer out of an account with a negative balance!");
        } else {
            if (balance.compareTo
                    (new ForeignCurrency("CAD", amount.convert("CAD").getAmount() -100))
                    < 0) {
                System.out.println("Cannot have a balance below -100!");
            } else {
                balance.subtract(amount);
                helpWrite(amount);
                System.out.println("Transaction successful!");
            }

        }
    }

}

