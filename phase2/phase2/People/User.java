package phase2.People;

import phase2.Accounts.*;
import phase2.Display.ATM;
import phase2.ForeignCurrency;
import phase2.Request.*;
import phase2.Transactions.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A user of the ATM.
 */
public class User {

    /**
     * The username for this user.
     */
    private String username;

    /**
     * The password for this user.
     */
    private String password;

    /**
     * The account manager for this user's accounts.
     */
    private AccountManager accounts;

    /**
     * Creates a new User.
     *
     * @param user     the username
     * @param password the password
     */
    public User(String user, String password) {
        username = user;
        this.password = password;
        accounts = new AccountManager();
        accounts.setOwner(this);
    }

    /**
     * Request creation of account.
     *
     * @param accountType  the account type
     * @param currencyCode the country code for the account
     */
    public void requestAccount(String accountType, String currencyCode) {
        Request req = new AccountRequest(this, accountType, currencyCode);
        ATM.b.addRequest(req);
    }

    /**
     * Request to undo transaction
     *
     * @param account the account
     * @param num     the transaction number to undo
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
     * Gets username.
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
     * Make a transaction.
     *
     * @param t the transaction
     */
    public void makeTransaction(Transaction t) {
        if (t.getIsApproved()) {
            System.out.println("Transaction made.");
            t.makeTransaction();
        } else {
            System.out.println("Transaction failed.");
        }
    }

    /**
     * Gets the list of transactions for this user.
     *
     * @return list of transactions
     */
    private ArrayList<Transaction> getTransactions() {
        return accounts.getTransactions();
    }

    /**
     * Gets the ith most recent transactions for this user.
     *
     * @param i the transaction number
     * @return the transaction
     */
     Transaction getTransaction(int i) {
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
    public ArrayList<Account> getAccountList(String s) {
        return accounts.getAccountList(s);
    }

    /**
     * Gets the account of a user given the account number.
     *
     * @param num the account number
     * @return the account
     */
    Account getAccount(int num) {
        return accounts.getAccount(num);
    }

    /**
     * Adds the user with username to the specified account.
     *
     * @param username the username
     * @param num      the account number
     */
    public void addAccountOwner(String username, int num) {
        Account a = getAccount(num);
        User u = UserManager.getUser(username);
        if (u != null) {
            u.getAccountManager().add(a);
            a.setNewOwner(u);
        }
    }

    @Override
    public String toString() {
        return username + "." + password;
    }

}
