package phase2.Accounts;

import phase2.ForeignCurrency;
import phase2.Transactions.Transaction;
import phase2.People.User;

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
    ForeignCurrency balance;

    private User owner1;

    private User owner2;

    public int accountNum;

    /**
     * The account's recent transactions.
     */
    final ArrayList<Transaction> transactions;

    /**
     * The date the account was created.
     */
    private final Calendar dateOfCreation;


    /**
     * Instantiates a new asset account.
     *
     * @param date         the date of creation
     * @param owner1       the owner 1
     * @param currencyCode the currency code
     */
    public AssetAccount(Calendar date, User owner1, String currencyCode, int num) {
        dateOfCreation = date;
        transactions = new ArrayList<>();
        this.owner1 = owner1;
        this.owner2 = null;
        balance = new ForeignCurrency(currencyCode, 0);
        accountNum = num;
    }

    /**
     * Instantiates a new asset account.
     *
     * @param date   the date of creation
     * @param owner1 the owner 1
     * @param owner2 the owner 2
     */
    public AssetAccount(Calendar date, User owner1, User owner2, String currencyCode, int num) {
        dateOfCreation = date;
        transactions = new ArrayList<>();
        this.owner1 = owner1;
        this.owner2 = owner2;
        balance = new ForeignCurrency(currencyCode, 0);
        accountNum = num;
    }

    /**
     * Returns the account balance.
     *
     * @return the balance
     */
    public ForeignCurrency getBalance() {
        return balance;
    }

    /**
     * Sets the account balance.
     *
     * @param newBalance the new balance
     */
    public void setBalance(ForeignCurrency newBalance){
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
        balance.add(transaction.getAmount());
        System.out.println("Transaction successful!");
    }

    /**
     * Adds amount from file to the balance.
     *
     * @param file name of the file that contains the amount
     */
    public void add(String file) {
        ForeignCurrency amount = helpRead(file);
        this.balance.add(amount);
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
    public ForeignCurrency helpRead(String file) {
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
    public void helpWrite(ForeignCurrency amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("phase2/phase2/Data/outgoing.txt"))) {
            writer.write(Double.toString(amount.convert("CAD").getAmount()));
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
