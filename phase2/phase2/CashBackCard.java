package phase2;

import java.util.ArrayList;
import java.util.Calendar;

public class CashBackCard extends CreditCard {

    private double balance = 0;

    protected CashBackCard(Calendar date, ArrayList<Transaction> transactions) {
        super(date, transactions);
    }

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
        if (balance > 0){
            balance += 1.25;
        }
    }

    @Override
    public void subtract(Transaction transaction) {
        balance += transaction.getAmount()*0.95;
        transactions.add(transaction);
        System.out.println("Transaction successful!");

    }

    @Override
    public void subtract(double amount) {
        balance += amount*0.95;
        helpWrite(amount*0.95);
        System.out.println("Transaction successful!");
    }
}