package phase2.Accounts;

import phase2.ForeignCurrency;
import phase2.Transactions.Transaction;
import phase2.People.User;
import java.util.Calendar;

/**
 * Represents a cash back card account.
 */
public class CashBackCard extends CreditCard {

    /**
     * Initializes a new cash back card.
     *
     * @param date         the date of creation
     * @param owner1       the first owner
     * @param currencyCode the currency code
     * @param num          the account number
     */
    public CashBackCard(Calendar date, User owner1, String currencyCode, int num) {
        super(date, owner1, currencyCode, num);
    }

    /**
     * Increases the account balance based on the amount of cash back.
     */
    public void increase() {
        ForeignCurrency balance = getBalance();
        if (balance.compareTo(new ForeignCurrency(balance.getCurrencyCode(),0)) >= 1){
            setBalance(balance.multiply(1.25));
        }
    }

    @Override
    public void subtract(Transaction transaction) {
        if (getCreditLimit().compareTo(transaction.getAmount().multiply(0.95)) > 0) {
            getBalance().add(transaction.getAmount().multiply(0.95));
            setBalance(getBalance());
            decreaseCreditLimit(transaction.getAmount().multiply(0.95));
            transactions.add(transaction);
            System.out.println("Transaction successful!");
        } else {
            System.out.println("There isn't enough credit on your account to complete this transaction.");
        }
    }

    @Override
    public void subtract(ForeignCurrency amount) {
        if (getCreditLimit().compareTo(amount.multiply(0.95)) > 0) {
            getBalance().add(amount.multiply(0.95));
            setBalance(getBalance());
            decreaseCreditLimit(amount.multiply(0.95));
            helpWrite(amount.multiply(0.95));
            System.out.println("Transaction successful!");
        } else {
            System.out.println("There isn't enough credit on your account to complete this transaction.");
        }
    }
}