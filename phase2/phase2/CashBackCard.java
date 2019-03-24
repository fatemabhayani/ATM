package phase2;

import java.util.ArrayList;
import java.util.Calendar;

public class CashBackCard extends CreditCard {

    public CashBackCard(Calendar date) {
        super(date);
    }

    public CashBackCard(Calendar date, User owner1) {
        super(date, owner1);
    }

    public CashBackCard(Calendar date, User owner1, User owner2) {
        super(date, owner1, owner2);
    }

    public void increase(){
        double balance = getBalance();
        if (balance > 0){
            setBalance(balance + 1.25);
        }
    }

    @Override
    public void subtract(Transaction transaction) {
        if (getCreditLimit() > transaction.getAmount()*0.95) {
            setBalance(getBalance() + transaction.getAmount() * 0.95);
            decreaseCreditLimit(transaction.getAmount() * 0.95);
            transactions.add(transaction);
            System.out.println("Transaction successful!");
        } else {
            System.out.println("There isn't enough credit limit on your account to complete this transaction");
        }
    }

    @Override
    public void subtract(double amount) {
        if (getCreditLimit() > amount * 0.95) {
            setBalance(getBalance() + amount * 0.95);
            decreaseCreditLimit(amount * 0.95);
            helpWrite(amount * 0.95);
            System.out.println("Transaction successful!");
        } else {
            System.out.println("There isn't enough credit limit on your account to complete this transaction");
        }
    }
}