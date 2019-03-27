package phase2.People;

import phase2.Accounts.Account;
import phase2.Display.ATM;
import phase2.Request.*;
import phase2.Transactions.Transaction;
import phase2.Accounts.AccountManager;

import java.io.Serializable;
import java.util.ArrayList;

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
    }

    /**
     * Request creation of account.
     *
     * @param accountType the account type
     */
    public void requestAccount(String accountType, String currencyCode) {
        Request req = new AccountRequest(this, accountType, currencyCode);
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
            System.out.println("Transaction made.");
            t.makeTransaction();
        } else {
            System.out.println("Transaction failed.");
        }
    }

    /**
     * Make deposit.
     *
     * @param t the transaction
     */
    public void makeDeposit(Transaction t){
        if (t.getIsApproved()) {
            System.out.println("Transaction made.");
            t.makeTransaction();
        } else {
            System.out.println("Transaction failed.");
        }
    }

    /**
     * Make withdrawal.
     *
     * @param t the transaction
     */
    public void makeWithdrawal(Transaction t){
        if (t.getIsApproved()) {
            System.out.println("Transaction made.");
            t.makeTransaction();
        } else {
            System.out.println("Transaction failed.");
        }
    }

    /**
     * Make bill payment.
     *
     * @param t the transaction
     */
    public void makeBillPayment(Transaction t) {
        if (t.getIsApproved()) {
            System.out.println("Transaction made.");
            t.makeTransaction();
        } else {
            System.out.println("Transaction failed.");
        }
    }

    private ArrayList<Transaction> getTransactions() {
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
    public ArrayList getAccountList(String s) {
        return accounts.getAccountList(s);
    }

    /**
     * Gets the accounts of a user given the account number.
     *
     * @param num the account number
     * @return the account
     */
    public Account getAccount(int num) {
        return accounts.getAccount(num);
    }

    @Override
    public String toString() {
        return username + "." + password;
    }

}
