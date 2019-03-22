package phase2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * The Credit card, type of Account
 */
public class CreditCard implements Account {
    private User owner1;
    private User owner2;
    private double balance = 0;
    protected ArrayList<Transaction> transactions;
    private Calendar date;

    /**
     * Instantiates a previous Credit card.
     *
     * @param transactions
     * @param date the date of creation
     */
    protected CreditCard(Calendar date, ArrayList<Transaction> transactions) {
        this.transactions = transactions;
        this.date = date;
    }

    public CreditCard(Calendar date) {
        this.date = date;
        transactions = new ArrayList<>();
    }

    public CreditCard(Calendar date, User owner1) {
        this.date = date;
        transactions = new ArrayList<>();
        this.owner1 = owner1;
        owner2 = null;
    }

    public CreditCard(Calendar date, User owner1, User owner2) {
        this.date = date;
        transactions = new ArrayList<>();
        this.owner1 = owner1;
        this.owner2 = owner2;
    }

    public double getBalance() { return this.balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public int helpRead(String file){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String info = reader.readLine();
            char value = info.charAt(0);
            char number = info.charAt(2);
            int denomination = Character.getNumericValue(value);
            int volume = Character.getNumericValue(number);
            return volume * denomination;

        } catch (Exception e){
            System.out.println("There was an error");
            return -1;
        }
    }

    public void helpWrite(double amount){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("outgoing.txt"))){
            writer.write(Double.toString(amount));
        } catch(Exception e){
            System.out.println("there was an error");
        }
    }

    public void subtract(Transaction transaction) {
        balance += transaction.getAmount();
        transactions.add(transaction);
        System.out.println("Transaction successful!");

    }

    public void subtract(double amount) {
        balance += amount;
        helpWrite(amount);
        System.out.println("Transaction successful!");
    }

    public void add(Transaction transaction) {
        balance -= transaction.getAmount();
        transactions.add(transaction);
        System.out.println("Transaction successful!");
    }

    public void add(String file) {
        int amount = helpRead(file);
        balance -= amount;
        System.out.println("Transaction successful!");
    }

    public String getCreationDate() {return this.date.toString();}

    /**
     * Returns the ith most recent transaction.
     *
     * @return the transaction at index i of transactions
     */
    public Transaction getPastTransaction(int i) { return transactions.get(i); }

    public String getSummary() {
        return "Debt account created on" + getCreationDate() + "with transactions to date " +
                transactions.toString();
    }

}
