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
     * The Line credit accounts.
     */
    ArrayList<LineOfCredit> lineCreditAccounts;
    /**
     * The Credit card accounts.
     */
    ArrayList<CreditCard> creditCardAccounts;
    /**
     * The Chequing accounts.
     */
    ArrayList<Chequing> chequingAccounts;
    /**
     * The Savings accounts.
     */
    ArrayList<Savings> savingsAccounts;

    ArrayList<CashBackCard> cashbackCardAccounts;

    private ArrayList<Transaction> transactions;

    public AccountManager accounts;


    /**
     * Creates a new User.
     *
     * @param user the username
     * @param pass the password
     */
    public User(String user, String pass) {
        username = user;
        password = pass;
        chequingAccounts = new ArrayList<>();
        creditCardAccounts = new ArrayList<>();
        lineCreditAccounts = new ArrayList<>();
        savingsAccounts = new ArrayList<>();
        transactions = new ArrayList<>();
        cashbackCardAccounts = new ArrayList<>();
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


    public Transaction getTransaction(int i) {
        return transactions.get(i);
    }

    /**
     * Balance summary
     *
     * @return the string that summarizes the balance in each account of user
     */
    public String balanceSummary() {
        StringBuilder sBuilder = new StringBuilder();
        for (Account account : lineCreditAccounts) {
            sBuilder.append("Line of Credit account: ").append(account.getBalance()).append("\n");
        }
        StringBuilder sBuilder1 = new StringBuilder(sBuilder.toString());
        for (Account account : creditCardAccounts) {
            sBuilder1.append("Credit Card account: ").append(account.getBalance()).append("\n");
        }
        StringBuilder sBuilder2 = new StringBuilder(sBuilder1.toString());
        for (Account account : savingsAccounts) {
            sBuilder2.append("Savings account: ").append(account.getBalance()).append("\n");
        }
        StringBuilder sBuilder3 = new StringBuilder(sBuilder2.toString());
        for (Account account : chequingAccounts) {
            sBuilder3.append("Chequing account: ").append(account.getBalance()).append("\n");
        }
        StringBuilder sBuilder4 = new StringBuilder(sBuilder3.toString());
        for (Account account : cashbackCardAccounts)
            sBuilder4.append("Cash Back Card account: ").append(account.getBalance()).append("\n");
        String s = sBuilder4.toString();
        s = s + ("Total balance in Canadian dollars: ");
        return s;
    }

    /**
     * Gets all the accounts of a user of a particular type
     *
     * @param s lc if the user want to get Line of Credit Accounts,
     *          cc if user wants credit card accounts,
     *          c if the user wants the chequing accounts
     *          s if the user wants the savings accounts
     * @return the account specified by user, if not specified, return empty array list
     */
    public ArrayList getAccount(String s) {
        switch (s) {
            case ("lc"):return lineCreditAccounts;
            case ("cc"):return creditCardAccounts;
            case ("c"):return chequingAccounts;
            case ("s"):return savingsAccounts;
            case ("cb"):return cashbackCardAccounts;
            default: return new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return "ATM user " + username;
    }

}
