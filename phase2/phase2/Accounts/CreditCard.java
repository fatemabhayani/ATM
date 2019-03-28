package phase2.Accounts;

import phase2.ForeignCurrency;
import phase2.Transactions.Transaction;
import phase2.People.User;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents a credit card account.
 */
public class CreditCard implements Account {

    /**
     * The account balance.
     */
    private ForeignCurrency balance;

    /**
     * The account owners.
     */
    private User owner1;
    private User owner2;

    /**
     * The account number.
     */
    public int accountNum;

    /**
     * The account transactions.
     */
    final ArrayList<Transaction> transactions;

    /**
     * The date the account was created.
     */
    private final Calendar dateOfCreation;

    /**
     * The credit limit for this account.
     */
    private ForeignCurrency creditLimit;

    /**
     * Instantiates a new credit card account.
     *
     * @param date         the date of creation
     * @param owner1       the first owner
     * @param currencyCode the currency code
     * @param num          the account number
     */
    public CreditCard(Calendar date, User owner1, String currencyCode, int num) {
        this.dateOfCreation = date;
        transactions = new ArrayList<>();
        this.owner1 = owner1;
        owner2 = null;
        balance = new ForeignCurrency(currencyCode, 0);
        double limit = new ForeignCurrency("CAD", 5000).convert(currencyCode).getAmount();
        creditLimit = new ForeignCurrency(currencyCode, limit);
        accountNum = num;
    }

    /**
     * Returns this account's balance.
     *
     * @return the balance
     */
    public ForeignCurrency getBalance() { return this.balance; }

    /**
     * Sets this account's balance.
     *
     * @param balance the new balance
     */
    public void setBalance(ForeignCurrency balance) { this.balance = balance; }

    /**
     * Returns the list of this account's transactions.
     *
     * @return the list of transactions
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Reads from file to extract the amount.
     *
     * @param file the file
     * @return the amount value
     */
    public ForeignCurrency helpRead(String file) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String info = reader.readLine();
            char value = info.charAt(0);
            char number = info.charAt(2);
            int denomination = Character.getNumericValue(value);
            int volume = Character.getNumericValue(number);
            return new ForeignCurrency("CAD", volume * denomination);

        } catch (Exception e) {
            System.out.println("There was an error");
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
            writer.write(Double.toString(amount.getAmount()));
        } catch(Exception e){
            System.out.println("There was an error");
        }
    }

    /**
     * Sets a new owner.
     *
     * @param owner2 the new owner
     */
    public void setNewOwner(User owner2){
        this.owner2 = owner2;
    }

    /**
     * Decreases the account balance by the amount in the transaction.
     *
     * @param transaction the transaction being made
     */
    public void subtract(Transaction transaction) {
        if (creditLimit.compareTo(transaction.getAmount()) > 0) {
            balance.add(transaction.getAmount());
            decreaseCreditLimit(transaction.getAmount());
            System.out.println("Transaction successful!");
        } else {
            System.out.println("There isn't enough credit limit on your account to complete this transaction");
        }
    }

    /**
     * Subtracts the amount from the balance.
     *
     * @param amount the amount
     */
    public void subtract(ForeignCurrency amount) {
        if (creditLimit.compareTo(amount) > 0) {
            balance.add(amount);
            helpWrite(amount);
            decreaseCreditLimit(amount);
            System.out.println("Transaction successful!");
        } else {
            System.out.println("There isn't enough credit on your account to complete this transaction");
        }
    }

    /**
     * Increases the balance by the amount in transaction.
     *
     * @param transaction the transaction
     */
    public void add(Transaction transaction) {
        balance.subtract(transaction.getAmount());
        increaseCreditLimit(transaction.getAmount());
        System.out.println("Transaction successful!");
    }

    /**
     * Adds amount from file to the balance.
     *
     * @param file name of the file that contains the amount
     */
    public void add(String file) {
        ForeignCurrency amount = helpRead(file);
        balance.subtract(amount);
        increaseCreditLimit(amount);
        System.out.println("Transaction successful!");
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
     * Returns the ith most recent transaction.
     *
     * @return the transaction at index i of transactions
     */
    public Transaction getPastTransaction(int i) { return transactions.get(i); }

    /**
     * Adds a new transaction to the list of this account's transactions.
     */
    public void addTransaction(Transaction t){
        transactions.add(0, t);
    }

    /**
     * Decreases the credit limit.
     *
     * @param creditLimit the new credit limit
     */
    public void decreaseCreditLimit(ForeignCurrency creditLimit) {
        this.creditLimit.subtract(creditLimit);
    }

    /**
     * Increases the credit limit.
     *
     * @param creditLimit the new credit limit
     */
    public void increaseCreditLimit(ForeignCurrency creditLimit) {
        this.creditLimit.add(creditLimit);
    }

    /**
     * Returns the credit limit.
     *
     * @return the new credit limit
     */
    ForeignCurrency getCreditLimit() {
        return creditLimit;
    }

    /**
     * Returns the string representation of the transactions.
     *
     * @return the transaction string
     */
    public String transactionString() {
        return "";
    }

    @Override
    public String toString() {
        return accountNum + " " + balance.toString() + " " + getCreationDate();
    }
}
