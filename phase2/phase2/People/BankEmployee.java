package phase2.People;

import phase2.Display.ATM;
import phase2.Transactions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * The type Bank employee.
 */
public abstract class BankEmployee {

    /**
     * The username for this employee.
     */
    private String username;

    /**
     * The password for this employee.
     */
    private String password;

    /**
     * Creates a new bank employee.
     *
     * @param username the username
     * @param password the password
     */
    BankEmployee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username of the bank employee.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the bank employee.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Undoes the ith most recent transaction by a user, where 0 represents
     * the most recent.
     *
     * @param user the user
     * @param i    the index of the most recent transaction
     */
    public void undoTransaction(User user, int i) {
        Transaction t = user.getTransaction(i);
        if (t.getClass().isInstance(Bill.class)) {
            System.out.println("Cannot undo a bill payment!");
        }
        t.undoTransaction();
    }

    /**
     * Restocks the cash machine based on the contents of alerts.txt.
     */
    public void restockCashMachine() {
        int index;
        String s;
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/alerts.txt"))) {
            s = reader.readLine();
            while (s != null) {
                System.out.println(s);
                index = getIndex(s);
                ATM.c.increaseBills(index, 20);
                s = reader.readLine();
            }
            System.out.println("No more alerts!");
        } catch (Exception e) {
            System.out.println("File handling error.");
        }
        deleteAlerts();
        System.out.println("The cash machine has been restocked!");
    }

    /**
     * Deletes the contents of alerts.txt.
     */
    private void deleteAlerts() {
        try (PrintWriter writer = new PrintWriter("phase2/phase2/Data/alerts.txt")) {
            writer.print("");
        } catch (Exception ignored) {}
    }

    /**
     * Returns the index of the denomination that needs to be restocked
     * in the cash machine.
     *
     * @param s the line from alerts.txt
     */
    private int getIndex(String s) {
        String number = s.substring(14, 16).replaceAll("//s", "");
        int denom = Integer.valueOf(number);
        switch (denom) {
            case 50: return 0;
            case 20: return 1;
            case 10: return 2;
            case 5: return 3;
            default: System.out.println("Not a valid denomination!");
                return -1;
        }
    }
}
