package phase2;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents an asset account.
 */
public abstract class AssetAccount implements Account, Serializable {

    /**
     * The account balance.
     */
    protected double balance;

    private User owner1;

    private User owner2;

    /**
     * The account's recent transactions.
     */
    protected ArrayList<Transaction> transactions;

    /**
     * The date the account was created.
     */
    private Calendar dateOfCreation;

    /**
     * Instantiates a new asset account.
     *
     * @param date the date of creation
     */
    public AssetAccount(Calendar date) {
        dateOfCreation = date;
        transactions = new ArrayList<>();
    }

    /**
     * Instantiates a new asset account.
     *
     * @param date the date of creation
     */
    public AssetAccount(Calendar date, User owner1) {
        dateOfCreation = date;
        transactions = new ArrayList<>();
        this.owner1 = owner1;
        this.owner2 = null;
    }

    /**
     * Instantiates a new asset account.
     *
     * @param date the date of creation
     */
    public AssetAccount(Calendar date, User owner1, User owner2) {
        dateOfCreation = date;
        transactions = new ArrayList<>();
        this.owner1 = owner1;
        this.owner2 = owner2;
    }

    /**
     * Returns the account balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the account balance.
     *
     * @param newBalance the new balance
     */
    public void setBalance(double newBalance){
        balance = newBalance;
    }

    /**
     * Returns the ith most recent transaction.
     *
     * @return the transaction at index i of transactions
     */
    public Transaction getPastTransaction(int i) { return transactions.get(i); }

    /**
     * Decreases the account balance by the amount in the transaction.
     *
     * @param transaction the transaction being made
     */
    public abstract void subtract(Transaction transaction);

    /**
     * Increases the balance by the amount in transaction.
     *
     * @param transaction the transaction
     */
    public void add(Transaction transaction) {
        transactions.add(0, transaction);
        balance += transaction.getAmount();
        System.out.println("Transaction successful!");
    }

    /**
     * Adds amount from file to the balance.
     *
     * @param file name of the file that contains the amount
     */
    public void add(String file) {
        int amount = helpRead(file);
        this.balance += amount;
    }

    /**
     * Returns the list of this account's transactions.
     *
     * @return the transaction list
     */
    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }

    /**
     * Reads from file to extract the amount.
     *
     * @param file the file
     * @return the int value of amount
     */
    public int helpRead(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s = reader.readLine();
            int denomination = Character.getNumericValue(s.charAt(0));
            int volume = Character.getNumericValue(s.charAt(2));
            return volume * denomination;
            // TODO: what does volume represent in this method? what is it reading?
        } catch (Exception e) {
            System.out.println("There was an error!");
            return -1;
        }
    }

    /**
     * Writes an amount into the file.
     *
     * @param amount the amount
     */
    public void helpWrite(double amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("phase1/phase1/outgoing.txt"))) {
            writer.write(Double.toString(amount));
        } catch(Exception e) {
            System.out.println("There was an error!");
        }
    }

    @Override
    public String toString() {
        return "Asset account created on: " + dateOfCreation.toString() + "\n" + "Last transaction: " +
                getPastTransaction(0).toString();
    }

}
