package phase2;

import java.util.Calendar;
import java.util.Locale;

public class CashBackCard extends CreditCard {

    public CashBackCard(Calendar date, Locale locale) {
        super(date, locale);
    }

    public CashBackCard(Calendar date, User owner1, Locale locale) {
        super(date, owner1, locale);
    }

    public CashBackCard(Calendar date, User owner1, User owner2, Locale locale) {
        super(date, owner1, owner2, locale);
    }

    public void increase(){
        ForeignCurrency balance = getBalance();
        if (balance.compareTo(new ForeignCurrency(balance.getLocale(),0)) >= 1){
            setBalance(balance.multiply(1.25));
        }
    }

    @Override
    public void subtract(Transaction transaction) {
        if (getCreditLimit().compareTo(transaction.getAmount().multiply(0.95)) == 1) {
            getBalance().add(transaction.getAmount().multiply(0.95));
            setBalance(getBalance());
            decreaseCreditLimit(transaction.getAmount().multiply(0.95));
            transactions.add(transaction);
            System.out.println("Transaction successful!");
        } else {
            System.out.println("There isn't enough credit limit on your account to complete this transaction");
        }
    }

    @Override
    public void subtract(ForeignCurrency amount) {
        if (getCreditLimit().compareTo(amount.multiply(0.95)) == 1) {
            getBalance().add(amount.multiply(0.95));
            setBalance(getBalance());
            decreaseCreditLimit(amount.multiply(0.95));
            helpWrite(amount.multiply(0.95));
            System.out.println("Transaction successful!");
        } else {
            System.out.println("There isn't enough credit limit on your account to complete this transaction");
        }
    }
}