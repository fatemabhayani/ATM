package phase2.Accounts;

import phase2.Tradable.ForeignCurrency;
import phase2.Tradable.Tradable;
import phase2.Transactions.Transaction;
import phase2.People.User;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents an asset account.
 */
public abstract class AssetAccount implements Account {

    /**
     * The account balance.
     */
    Tradable balance;

    /**
     * The first account owner.
     */
    private User owner1;

    /**
     * The second account owner.
     */
    private User owner2;

    /**
     * The account number.
     */
    int accountNum;

    /**
     * The account manager.
     */
    private final ArrayList<Transaction> transactions;

    /**
     * The date the account was created.
     */
    private final Calendar dateOfCreation;

    /**
     * Instantiates a new asset account.
     *
     * @param date         the date of creation
     * @param owner1       the first owner
     * @param identifier   the 3 digit identifier for the object
     * @param num          the account number
     */
    public AssetAccount(Calendar date, User owner1, String identifier, int num) {
        dateOfCreation = date;
        transactions = new ArrayList<>();
        this.owner1 = owner1;
        this.owner2 = null;
        balance = new ForeignCurrency(identifier, 0);
        accountNum = num;
    }


    /**
     * Returns the account balance.
     *
     * @return the balance
     */
    public Tradable getBalance() {
        return balance;
    }

    /**
     * Sets the account balance.
     *
     * @param balance the new balance
     */
    public void setBalance(Tradable balance){
        this.balance = balance;
    }

    /**
     * Sets a new owner.
     *
     * @param owner2 the second owner
     */
    public void setNewOwner(User owner2){
        this.owner2 = owner2;
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
     * Decreases the account balance by amount.
     *
     * @param amount the amount being subtracted
     */
    public abstract void subtract(Tradable amount);

    /**
     * Increases the balance by the amount in transaction.
     *
     * @param transaction the transaction
     */
    public void add(Transaction transaction) {
        balance.add(transaction.getAmount());
        System.out.println("Transaction successful!");
    }

    /**
     * Adds amount from file to the balance.
     *
     * @param file name of the file that contains the amount
     */
    public void add(String file) {
        Tradable amount = helpRead(file);
        balance.add(amount);
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
     * Adds a new transaction to the list of this account's transactions.
     */
    public void addTransaction(Transaction t){
        transactions.add(t);
    }

    /**
     * Reads from file to extract the amount.
     *
     * @param file the file
     * @return the amount value
     */
    public Tradable helpRead(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s = reader.readLine();
            int denomination = Character.getNumericValue(s.charAt(0));
            int volume = Character.getNumericValue(s.charAt(2));
            return new ForeignCurrency("CAD", volume * denomination);
        } catch (Exception e) {
            System.out.println("There was an error!");
            return new ForeignCurrency("CAD", -1);
        }
    }

    /**
     * Writes an amount into the file.
     *
     * @param amount the amount
     */
    public void helpWrite(Tradable amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("phase2/phase2/Data/outgoing.txt"))) {
            writer.write(Double.toString(amount.convert("CAD").getAmount()));
        } catch(Exception e) {
            System.out.println("There was an error!");
        }
    }

    /**
     * Returns the date of creation for this account.
     *
     * @return the date of creation
     */
    private String getCreationDate() {
        return dateOfCreation.get(Calendar.YEAR) + "/" + (dateOfCreation.get(Calendar.MONTH) + 1) + "/" +
                dateOfCreation.get(Calendar.DAY_OF_MONTH) + " " + dateOfCreation.get(Calendar.HOUR_OF_DAY) + ":" +
                dateOfCreation.get(Calendar.MINUTE) + ":" + dateOfCreation.get(Calendar.SECOND);
    }

    /**
     * Returns the string representation of the transactions.
     *
     * @return the transaction string
     */
    @Override
    public String transactionString() {
        StringBuilder s = new StringBuilder();
        for (Transaction t : transactions ) {
            s.append("\t").append(t.toString());
        }
        return s.toString();
    }

    /**
     * Returns the account number.
     *
     * @return the account number
     */
    public int getAccountNum() { return accountNum; }

    @Override
    public String toString() {
        return accountNum + " " + balance.toString() + " " + getCreationDate();
    }

}
