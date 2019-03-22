package phase2;

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
    double getBalance();

    /**
     * Sets the account balance.
     *
     * @param balance the new balance
     */
    void setBalance(double balance);

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
    void subtract(double amount);

    /**
     * Increases the balance by the amount in transaction.
     *
     * @param transaction the transaction
     */
    void add(Transaction transaction);

    /**
     * Adds amount from file to the balance.
     *
     * @param file name of the file that contains the amount
     */
    void add(String file);

    /**
     * Returns this accounts list of transactions.
     *
     * @return the list of transactions
     */
    ArrayList<Transaction> getTransactions();

    /**
     * Returns the ith most recent transaction.
     *
     * @return transaction at index i
     */
    Transaction getPastTransaction(int i);

    /**
     * Reads from file to extract the amount.
     *
     * @param file the file
     * @return the int value of amount
     */
    int helpRead(String file);

    /**
     * Writes an amount into the file.
     *
     * @param amount the amount
     */
    void helpWrite(double amount);
}
