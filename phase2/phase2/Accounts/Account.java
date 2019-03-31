package phase2.Accounts;

import phase2.ForeignCurrency;
import phase2.Transactions.Transaction;
import phase2.People.User;
import java.util.ArrayList;

/**
 * The interface for a bank account.
 */
public interface Account {

    /**
     * Gets the account balance.
     *
     * @return the balance
     */
    ForeignCurrency getBalance();

    /**
     * Sets the account balance.
     *
     * @param balance the new balance
     */
    void setBalance(ForeignCurrency balance);

    /**
     * Decreases the balance by the amount in transaction.
     *
     * @param transaction the transaction
     */
    void subtract(Transaction transaction);

    /**
     * Subtracts the amount from the balance.
     *
     * @param amount the amount
     */
    void subtract(ForeignCurrency amount);

    /**
     * Increases the balance by the amount in transaction.
     *
     * @param transaction the transaction
     */
    void add(Transaction transaction);

    /**
     * Adds the amount from file to the balance.
     *
     * @param file name of the file that contains the amount
     */
    void add(String file);

    /**
     * Returns this account's list of transactions.
     *
     * @return the list of transactions
     */
    ArrayList<Transaction> getTransactions();

    /**
     * Returns the ith most recent transaction.
     *
     * @param i the index of past transaction
     * @return transaction at index i
     */
    Transaction getPastTransaction(int i);

    /**
     * Reads from file to extract the amount.
     *
     * @param file the file
     * @return the amount
     */
    ForeignCurrency helpRead(String file);

    /**
     * Writes an amount into the file.
     *
     * @param amount the amount
     */
    void helpWrite(ForeignCurrency amount);

    /**
     * Adds a second owner to the account.
     *
     * @param owner the second owner
     */
    void setNewOwner(User owner);

    /**
     * Adds a new transaction to the list of this account's transactions.
     */
    void addTransaction(Transaction t);

    /**
     * Returns the account number.
     *
     * @return the account number
     */
    int getAccountNum();

    String transactionString();
}
