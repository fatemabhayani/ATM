package phase2.Accounts;

import phase2.Tradable.ForeignCurrency;
import phase2.Tradable.Tradable;
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
    private Tradable balance;

    /**
     * The account owners.
     */
    private User owner1;
    private User owner2;

    /**
     * The account number.
     */
    int accountNum;

    /**
     * The account transactions.
     */
    private final ArrayList<Transaction> transactions;

    /**
     * The date the account was created.
     */
    private final Calendar dateOfCreation;

    /**
     * The credit limit for this account.
     */
    private Tradable creditLimit;

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
    public Tradable getBalance() { return this.balance; }

    /**
     * Sets this account's balance.
     *
     * @param balance the new balance
     */
    public void setBalance(Tradable balance) { this.balance = balance; }

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
    public Tradable helpRead(String file) {
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
    public void helpWrite(Tradable amount) {
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
    public void subtract(Tradable amount) {
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
        Tradable amount = helpRead(file);
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
    public void decreaseCreditLimit(Tradable creditLimit) {
        this.creditLimit.subtract(creditLimit);
    }

    /**
     * Increases the credit limit.
     *
     * @param creditLimit the new credit limit
     */
    public void increaseCreditLimit(Tradable creditLimit) {
        this.creditLimit.add(creditLimit);
    }

    /**
     * Returns the credit limit.
     *
     * @return the new credit limit
     */
    Tradable getCreditLimit() {
        return creditLimit;
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
