package phase2.People;

import phase2.Display.ATM;
import phase2.People.User;
import phase2.Transactions.Bill;
import phase2.Transactions.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Serializable;

public abstract class BankEmployee implements Serializable {

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
     * Undoes the nth recent transaction by a user, where 0 represents
     * most recent.
     *
     * @param user the user
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
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/alerts.txt"))) {
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
        try (PrintWriter writer = new PrintWriter("phase1/phase1/alerts.txt")) {
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
