package phase2.Accounts;

import phase2.ForeignCurrency;
import phase2.Transactions.Transaction;
import phase2.People.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * The Credit card, type of Account
 */
public class CreditCard implements Account, Serializable {
    private User owner1;
    private User owner2;
    private ForeignCurrency balance;
    ArrayList<Transaction> transactions;
    private Calendar date;
    private ForeignCurrency creditLimit;


    /**
     * Instantiates a previous Credit card.
     * @param date the date of creation
     */
    public CreditCard(Calendar date, String currencyCode) {
        this.date = date;
        transactions = new ArrayList<>();
        balance = new ForeignCurrency(currencyCode, 0);
        double limit = new ForeignCurrency("CAD", 5000).convert(currencyCode).getAmount();
        creditLimit = new ForeignCurrency(currencyCode, limit);
    }

    public CreditCard(Calendar date, User owner1, String currencyCode) {
        this.date = date;
        transactions = new ArrayList<>();
        this.owner1 = owner1;
        owner2 = null;
        balance = new ForeignCurrency(currencyCode, 0);
        double limit = new ForeignCurrency("CAD", 5000).convert(currencyCode).getAmount();
        creditLimit = new ForeignCurrency(currencyCode, limit);
    }

    public CreditCard(Calendar date, User owner1, User owner2, String currencyCode) {
        this.date = date;
        transactions = new ArrayList<>();
        this.owner1 = owner1;
        this.owner2 = owner2;
        balance = new ForeignCurrency(currencyCode, 0);
        double limit = new ForeignCurrency("CAD", 5000).convert(currencyCode).getAmount();
        creditLimit = new ForeignCurrency(currencyCode, limit);
    }

    public ForeignCurrency getBalance() { return this.balance; }
    public void setBalance(ForeignCurrency balance) { this.balance = balance; }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public ForeignCurrency helpRead(String file){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String info = reader.readLine();
            char value = info.charAt(0);
            char number = info.charAt(2);
            int denomination = Character.getNumericValue(value);
            int volume = Character.getNumericValue(number);
            return new ForeignCurrency("CAD", volume * denomination);

        } catch (Exception e){
            System.out.println("There was an error");
            return new ForeignCurrency("CAD", -1);
        }
    }

    public void helpWrite(ForeignCurrency amount){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("outgoing.txt"))){
            writer.write(Double.toString(amount.getAmount()));
        } catch(Exception e){
            System.out.println("there was an error");
        }
    }

    public void subtract(Transaction transaction) {
        if (creditLimit.compareTo(transaction.getAmount()) == 1){
            balance.add(transaction.getAmount());
            transactions.add(transaction);
            decreaseCreditLimit(transaction.getAmount());
            System.out.println("Transaction successful!");
        } else{
            System.out.println("There isn't enough credit limit on your account to complete this transaction");
        }
    }

    public void subtract(ForeignCurrency amount) {
        if (creditLimit.compareTo(amount) > 0) {
            balance.add(amount);
            helpWrite(amount);
            decreaseCreditLimit(amount);
            System.out.println("Transaction successful!");
        } else {
            System.out.println("There isn't enough credit limit on your account to complete this transaction");
        }
    }

    public void add(Transaction transaction) {
        balance.subtract(transaction.getAmount());
        transactions.add(transaction);
        increaseCreditLimit(transaction.getAmount());
        System.out.println("Transaction successful!");
    }

    public void add(String file) {
        ForeignCurrency amount = helpRead(file);
        balance.subtract(amount);
        increaseCreditLimit(amount);
        System.out.println("Transaction successful!");
    }

    private String getCreationDate() {return this.date.toString();}

    /**
     * Returns the ith most recent transaction.
     *
     * @return the transaction at index i of transactions
     */
    public Transaction getPastTransaction(int i) { return transactions.get(i); }

    public String getSummary() {
        return "Debt account created on" + getCreationDate() + "with transactions to date " +
                transactions.toString() + "with credit limit " + creditLimit;
    }

    void decreaseCreditLimit(ForeignCurrency creditLimit) {
        this.creditLimit.subtract(creditLimit);
    }

    private void increaseCreditLimit(ForeignCurrency creditLimit) {
        this.creditLimit.add(creditLimit);
    }

    ForeignCurrency getCreditLimit() {
        return creditLimit;
    }
}
