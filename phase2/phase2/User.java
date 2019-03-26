package phase2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A user of the ATM.
 */
public class User implements Serializable {

    /**
     * The username for this user.
     */
    private String username;

    /**
     * The password for this user.
     */
    private String password;

    /**
     * The transactions for this user.
     */
    private ArrayList<Transaction> transactions;

    /**
     * The account manager for this user's accounts.
     */
    private AccountManager accounts;


    /**
     * Creates a new User.
     *
     * @param user the username
     * @param password the password
     */
    public User(String user, String password) {
        username = user;
        this.password = password;
        accounts = new AccountManager();
        transactions = new ArrayList<>();
    }

    /**
     * Request creation of account.
     *
     * @param accountType the account type
     */
    public void requestAccount(String accountType, Locale locale) {
        Request req = new AccountRequest(this, accountType, locale);
        ATM.b.addRequest(req);
    }

    /**
     * Request to undo transaction
     *
     * @param account the account
     */
    public void requestUndo(Account account, int num) {
        Request req = new UndoRequest(this, account, num);
        ATM.b.addRequest(req);
    }

    /**
     * Gets the account manager.
     *
     * @return the account manager
     */
    public AccountManager getAccountManager() {
        return accounts;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param newPass the new pass
     */
    public void setPassword(String newPass) {
        password = newPass;
    }

    /**
     * Make transfer.
     *
     * @param t the transaction
     */
    public void makeTransfer(Transaction t){
        if (t.getIsApproved()) {
            transactions.add(0, t);
            System.out.println("Transaction made.");
        } else {
            System.out.println("Transaction failed.");
        }
        t.makeTransaction();
    }

    /**
     * Make deposit.
     *
     * @param t the transaction
     */
    public void makeDeposit(Transaction t){
        if (t.getIsApproved()) {
            transactions.add(0, t);
            System.out.println("Transaction made.");
        } else {
            System.out.println("Transaction failed.");
        }
        t.makeTransaction();
    }

    /**
     * Make withdrawal.
     *
     * @param t the transaction
     */
    public void makeWithdrawal(Transaction t){
        if (t.getIsApproved()) {
            transactions.add(0, t);
            System.out.println("Transaction made.");
        } else {
            System.out.println("Transaction failed.");
        }
        t.makeTransaction();
    }

    /**
     * Make bill payment.
     *
     * @param t the transaction
     */
    public void makeBillPayment(Transaction t) {
        if (t.getIsApproved()) {
            transactions.add(0, t);
            System.out.println("Transaction made.");
        } else {
            System.out.println("Transaction failed.");
        }
        t.makeTransaction();
    }

    public ArrayList<Transaction> getTransactions() {
        return accounts.getTransactions();
    }

    public Transaction getTransaction(int i) {
        return getTransactions().get(i);
    }

    /**
     * Balance summary
     *
     * @return the string that summarizes the balance in each account of user
     */
    public String balanceSummary() {
        return accounts.balanceSummary();
    }

    /**
     * Gets all the accounts of a user of a particular type
     *
     * @param s the type of accounts
     * @return the list of accounts specified by user, returns the empty list if an invalid input is made
     */
    public ArrayList getAccount(String s) {
        return accounts.getAccount(s);
    }

    @Override
    public String toString() {
        return "USER: " + username;
    }

}
