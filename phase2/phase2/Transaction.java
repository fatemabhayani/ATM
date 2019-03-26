package phase2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Locale;

/**
 * The Transaction.
 */
public abstract class Transaction implements Serializable {

    /**
     * The Amount of the transaction.
     */
    protected ForeignCurrency amount;
    private Calendar timeOfTransaction;
    // Records whether transaction was valid or not
    private boolean approved;

    /**
     * Instantiates a new Transaction.
     *
     * @param amount            the amount
     * @param timeOfTransaction the time of transaction
     */
// pass in AtM time
    public Transaction(ForeignCurrency amount, Calendar timeOfTransaction) {
        setAmount(amount);
        setTimeOfTransaction(timeOfTransaction);
        setIsApproved();
        // transaction is made upon creation (if viable)
        if (this.approved) {
            makeTransaction();
        }
    }
    /**
     * Instantiates a new Transaction. Second constructor for deposit transactions.
     *
     * @param depositFile       the amount recorded in a .txt file
     * @param timeOfTransaction the time of transaction
     */
    public Transaction(String depositFile, Calendar timeOfTransaction) {
        ForeignCurrency deposit = readFile(depositFile);
        setAmount(deposit);
        setTimeOfTransaction(timeOfTransaction);
        setIsApproved();
        // transaction is made upon creation (if viable)
        if (this.approved) {
            makeTransaction();
        }
    }

    private void setIsApproved() {
        this.approved = transactionApproved();
    }

    /**
     * Get whether the transaction is approved.
     *
     * @return true if approved, false otherwise
     */
    public boolean getIsApproved() { return approved; }

    private void setAmount(ForeignCurrency amount){
        this.amount = amount;
    }

    /**
     * Gets amount of transaction
     *
     * @return the amount
     */
    public ForeignCurrency getAmount() {
        return amount;
    }

    private void setTimeOfTransaction(Calendar time){
        this.timeOfTransaction = time;
    }

    /**
     * Get time of transaction time.
     *
     * @return the time
     */
    public Calendar getTimeOfTransaction(){
        return timeOfTransaction;
    }
    /**
     * Reads .txt file and accumulates amounts deposited
     *
     * @return the accumulated amount recorded in the file.
     */
    private ForeignCurrency readFile(String textFile) {
        // Rep invariant:
        //      .txt file must record amounts in double format without other characters
        //      for example: 20.00
        //                   450.80 would be accepted while:
        //                   "forty dollars" || $450 is not.
        String inputAmount;
        double totalDeposit = 0.00;
        try {
            BufferedReader re = new BufferedReader(new FileReader(textFile));
            while ((inputAmount = re.readLine()) != null) {
                // add amount into total amount
                try {
                    double value = Double.parseDouble(inputAmount);
                    totalDeposit += value;
                } catch (Exception ex) {
                    System.out.println("Error: Invalid deposit request");
                }
            }
        }catch (IOException io) {
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
    public abstract boolean transactionApproved();
}

