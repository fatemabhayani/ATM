package phase2.Transactions;

import phase2.Tradable.*;
import phase2.Accounts.*;
import phase2.Tradable.Tradable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

/**
 * An abstract transaction.
 */
public abstract class Transaction implements Comparable<Transaction> {

    /**
     * The amount of the transaction.
     */
    Tradable amount;

    /**
     * The date of the transaction.
     */
    private Calendar timeOfTransaction;

    /**
     * Records whether or not the transaction is valid.
     */
    private boolean approved;

    /**
     * Instantiates a new transaction.
     *
     * @param amount            the amount
     * @param timeOfTransaction the time of transaction
     */
    Transaction(Tradable amount, Calendar timeOfTransaction) {
        this.amount = amount;
        this.timeOfTransaction = timeOfTransaction;
        this.approved = transactionApproved();
    }

    /**
     * Instantiates a new Transaction.
     * This is a second constructor for deposit transactions.
     *
     * @param depositFile       the amount recorded in a .txt file
     * @param timeOfTransaction the time of transaction
     */
    Transaction(String depositFile, Calendar timeOfTransaction) {
        amount = readFile(depositFile);
        this.timeOfTransaction = timeOfTransaction;
        this.approved = transactionApproved();
    }

    /**
     * Gets whether the transaction is approved.
     *
     * @return true if approved, false otherwise
     */
    public boolean getIsApproved() { return approved; }

    /**
     * Gets the account that has made this transaction.
     *
     * @return the account making the transaction
     */
    public abstract Account getTransactionAccount();

    /**
     * Gets amount of transaction.
     *
     * @return the amount
     */
    public Tradable getAmount() { return amount; }

    /**
     * Gets time of transaction.
     *
     * @return the time
     */
    Calendar getTimeOfTransaction() { return timeOfTransaction; }

    /**
     * Returns the date of this transaction.
     *
     * @return the date of creation
     */
    public String getTransactionTimestamp() {
        return timeOfTransaction.get(Calendar.YEAR) + "/" + (timeOfTransaction.get(Calendar.MONTH) + 1) + "/" +
                timeOfTransaction.get(Calendar.DAY_OF_MONTH) + " " + timeOfTransaction.get(Calendar.HOUR_OF_DAY) + ":" +
                timeOfTransaction.get(Calendar.MINUTE) + ":" + timeOfTransaction.get(Calendar.SECOND);
    }

    /**
     * Reads .txt file and accumulates amounts deposited
     *
     * @return the accumulated amount recorded in the file.
     */
    private Tradable readFile(String textFile) {
        // Rep invariant:
        //      .txt file must be formatted as follows:
        //      Deposit [amount] dollars into [User.username]'s account [account ID].
        String inputAmount;
        double totalDeposit = 0.00;
        try {
            BufferedReader re = new BufferedReader(new FileReader(textFile));
            while ((inputAmount = re.readLine()) != null) {
                String[] amountArray = inputAmount.split(" ");
                // removes all other characters except numbers
                inputAmount = amountArray[1].replaceAll("[^\\d. ]","");
                // add amount into total amount
                try {
                    double value = Double.parseDouble(inputAmount);
                    totalDeposit += value;
                } catch (Exception ex) {
                    System.out.println("Error: Invalid deposit request");
                }
            }
        } catch (IOException io) {
            System.out.println("Error:" + io);
        }
        return new ForeignCurrency("CAD", totalDeposit);
    }

    /**
     * Make a transaction.
     */
    public abstract void makeTransaction();

    /**
     * Undo a transaction.
     */
    public abstract void undoTransaction();

    /**
     * Transaction approved
     *
     * @return true if transaction approved, false otherwise
     */
    protected abstract boolean transactionApproved();

    /**
     * Compares the date of two transactions.
     *
     * @param t the second transaction
     * @return a negative value if t was made more recently, a positive value if t was made earlier,
     * and 0 if t was made at the same date
     */
    public int compareTo(Transaction t) {
        Calendar time1 = getTimeOfTransaction();
        Calendar time2 = t.getTimeOfTransaction();
        return time1.compareTo(time2);
    }
}

