package phase2.People;

import phase2.Accounts.*;
import phase2.Display.ATM;
import phase2.ForeignCurrency;
import phase2.Transactions.Deposit;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Manages the collection of bank users for the ATM.
 */
public class UserManager {

    /**
     * The number of accounts in the ATM.
     */
    public static int accountNum = 0;

    /**
     * Creates a new UserManager.
     * Since this is a utility class, the constructor is private.
     */
    private UserManager() {}

    /**
     * Checks whether username belongs to an ATM user or employee.
     *
     * @param username the username
     * @return true if and only if username belongs to a user or employee.
     */
    public static boolean authenticateUsername(String username) {
        boolean authenticated = false;
        for (User u: ATM.bankUsers) {
            if (username.equals(u.getUsername())) {
                authenticated = true;
            }
        }
        for (BankTeller t: ATM.bankEmployees) {
            if (username.equals(t.getUsername())) {
                authenticated = true;
            }
        }
        if (username.equals(ATM.b.getUsername())) {
            authenticated = true;
        }
        return authenticated;
    }

    /**
     * Checks whether username belongs to the BankManager.
     *
     * @param username the username
     * @return true if and only if username belongs to the bank manager.
     */
    public static boolean isBankManager(String username) {
        return username.equals(ATM.b.getUsername());
    }

    /**
     * Returns the next account number to be assigned and increases it by 1.
     *
     * @return the next account number to be assigned
     */
    public static int getNextAccountNum() {
        accountNum++;
        return accountNum - 1;
    }

    /**
     * Checks whether password matches that of the ATM user or employee.
     *
     * @param password the username
     * @param user     the user
     * @return true if and only if password is correct.
     */
    public static boolean authenticatePassword(String password, User user) {
        return password.equals(user.getPassword());
    }

    /**
     * Returns the ATM user with a given username, if one exists.
     *
     * @param username the username
     * @return the user
     */
    public static User getUser(String username) {
        for (User u: ATM.bankUsers) {
            if (username.equals(u.getUsername())) {
                return u;
            }
        }
        System.out.println("Such a user does not exist");
        return null;
    }

    /**
     * Returns the account of a given ATM user, given the account number.
     *
     * @param num the account number
     * @return the account
     */
    public static Account getUserAccount(int num) {
        for (User u : ATM.bankUsers) {
            if (u.getAccount(num) != null) {
                return u.getAccount(num);
            }
        }
        System.out.println("That is not a valid account number!");
        return null;
    }

    /**
     * Adds a user to the list of ATM bank users, with username and password.
     *
     * @param username the user's username
     * @param password the password
     */
    public static void addUser(String username, String password){
        User u = new User(username, password);
        ATM.bankUsers.add(u);
    }

    /**
     * Updates the savings and cash back accounts of every user.
     */
    public static void updateInterest() {
        for (User u: ATM.bankUsers) {
            ArrayList s = u.getAccountList("sv");
            for (Object a : s) {
                ((Savings) a).increase();
            }
            ArrayList cb = u.getAccountList("cb");
            for (Object c : cb){
                ((CashBackCard) c).increase();
            }
        }
    }
}

