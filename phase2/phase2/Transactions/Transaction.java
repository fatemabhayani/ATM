package phase2.Transactions;

import phase2.ForeignCurrency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

/**
 * An abstract transaction.
 */
public abstract class Transaction {

    /**
     * The amount of the transaction.
     */
    ForeignCurrency amount;

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
    Transaction(ForeignCurrency amount, Calendar timeOfTransaction) {
        this.amount = amount;
        this.timeOfTransaction = timeOfTransaction;
        this.approved = transactionApproved();
        // The transaction is made upon creation (if viable)
        if (this.approved) {
            makeTransaction();
        } else {
            System.out.println("This transaction is not approved.");
        }
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
        // The transaction is made upon creation (if viable)
        if (this.approved) {
            makeTransaction();
        }
    }

    /**
     * Gets whether the transaction is approved.
     *
     * @return true if approved, false otherwise
     */
    public boolean getIsApproved() { return approved; }

    /**
     * Gets amount of transaction.
     *
     * @return the amount
     */
    public ForeignCurrency getAmount() { return amount; }

    /**
     * Gets time of transaction.
     *
     * @return the time
     */
    public Calendar getTimeOfTransaction() { return timeOfTransaction; }

    /**
     * Reads .txt file and accumulates amounts deposited
     *
     * @return the accumulated amount recorded in the file.
     */
    private ForeignCurrency readFile(String textFile) {
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
}

