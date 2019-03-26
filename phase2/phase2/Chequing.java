package phase2;

import java.util.Calendar;
import java.util.Locale;

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
    public Chequing(boolean isPrimary, Calendar date, String currencyCode) {
        super(date, currencyCode);
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

