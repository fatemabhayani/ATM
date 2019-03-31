package phase2.People;

import phase2.CashMachine;
import phase2.Display.ATM;
import phase2.Request.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * The type Bank teller.
 */
class BankTeller extends User {
    // Start with fixed number (5) of BankTellers
    // username: teller1, ..., teller5
    // password: bestemployee1, ..., bestemployee5

    /**
     * Creates a new bank teller.
     *
     * @param username the teller's username
     * @param password the teller's password
     */
    public BankTeller(String username, String password) {
        super(username, password);
    }

    /**
     * Returns the undo request at a specified index in requests.
     *
     * @param i the index of requests
     * @return the request at index i
     */
    protected UndoRequest getRequest(int i) {
        return ATM.undoRequests.get(i);
    }

    /**
     * Returns number of requests in the manager's notifications.
     *
     * @return the number of requests
     */
    private int getNumberOfRequests() { return ATM.undoRequests.size(); }

    /**
     * Returns a summary of the bank manager's requests.
     *
     * @return the summary of requests
     */
    public String getRequestSummary() {
        if (ATM.undoRequests.size() == 0) {
            return "You have no requests.";
        }

        StringBuilder summary = new StringBuilder("You have " + getNumberOfRequests() + " requests.");
        int i = 0;
        for (UndoRequest req : ATM.undoRequests) {
            String line = i + req.toString() + "\n";
            summary.append(line);
            i++;
        }
        return summary.toString();
    }

    /**
     * Adds a request to the list of requests.
     *
     * @param request the request to add
     */
    public void addRequest(UndoRequest request) {
        ATM.undoRequests.add(request);
    }

    /**
     * Ignores a request at a specified index by deleting it from requests.
     *
     * @param i the index of requests
     */
    public void ignoreRequest(int i) {
        ATM.undoRequests.remove(i);
    }

    /**
     * Completes an undo request at a specified index and deletes it from requests.
     *
     * @param i the index of requests
     */
    public void completeRequest(int i) {
        ATM.undoRequests.get(i).resolveRequest();
        ATM.undoRequests.remove(i);
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
                CashMachine.getInstance().increaseBills(index, 20);
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
