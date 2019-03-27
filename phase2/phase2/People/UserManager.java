package phase2.People;

import phase2.Accounts.CashBackCard;
import phase2.Accounts.Savings;
import phase2.Display.ATM;
import java.util.ArrayList;

/**
 * Manages the collection of bank users for the ATM.
 */
public class UserManager {

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
    static boolean isBankManager(String username) {
        return username.equals(ATM.b.getUsername());
    }

    /**
     * Checks whether password matches that of the ATM user or employee.
     *
     * @param password the username
     * @param user the user
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
     * Adds a user to the list of ATM bank users, with username and password.
     *
     * @param username the user's username
     */
    public static void addUser(String username, String password){
        User u = new User(username, password);
        ATM.bankUsers.add(u);
    }

    /**
     * Updates the savings accounts of every user.
     */
    public static void updateSavings() {
        for (User u: ATM.bankUsers) {
            ArrayList s = u.getAccount("sv");
            for (Object a : s) {
                ((Savings) a).increase();
            }
            ArrayList cb = u.getAccount("cb");
            for (Object c : cb){
                ((CashBackCard) c).increase();
            }
        }
    }

}

