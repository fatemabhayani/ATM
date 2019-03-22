package phase2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * A bank manager for the ATM.
 */
public class BankManager extends BankEmployee {

    /**
     * Creates a new bank manager.
     */
    public BankManager() {
        super("bankmanager", "bestboss");
    }

    /**
     * Sets the date for the ATM.
     *
     * @param year the year
     * @param month the month
     * @param date the date
     */
    public void setDate(int year, int month, int date, int hour, int minute, int second) {
        ATM.clock.setDate(year, month, date, hour, minute, second);
    }

    /**
     * Restocks the cash machine based on the contents of alerts.txt.
     */
    public void restockCashMachine() {
        int index;
        String s;
        try (BufferedReader reader = new BufferedReader(new FileReader("phase1/phase1/alerts.txt"))) {
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
        } catch (Exception e) {}
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
