package phase2.People;

import phase2.ATMTime;
import phase2.CashMachine;
import phase2.Display.ATM;
import phase2.Request.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * A bank manager for the ATM.
 */
public class BankManager {

    /**
     * The single bank manager for the ATM.
     */
    private static BankManager manager = null;

    /**
     * The username for this employee.
     */
    private String username;

    /**
     * The password for this employee.
     */
    private String password;

    /**
     * The list of requests for the manager.
     */
    public ArrayList<UserRequest> userRequests;

    /**
     * The list of requests for the manager.
     */
    public ArrayList<AccountRequest> accountRequests;

    /**
     * Creates a new bank manager.
     */
    private BankManager() {
        username = "bankmanager";
        password = "bestboss";
        userRequests = new ArrayList<>();
        accountRequests = new ArrayList<>();
    }

    /**
     * Returns the single bank manager.
     */
    public static BankManager getInstance() {
        if (manager == null) {
            manager = new BankManager();
        }
        return manager;
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
     * Sets the date for the ATM.
     *
     * @param year   the year
     * @param month  the month
     * @param date   the date
     * @param hour   the hour
     * @param minute the minute
     * @param second the second
     */
    public void setDate(int year, int month, int date, int hour, int minute, int second) {
        ATMTime.getInstance().setDate(year, month, date, hour, minute, second);
    }

    /**
     * Returns the list of requests.
     *
     * @return the list of requests
     */
    public ArrayList<Request> getRequestList() {
        ArrayList<Request> r = new ArrayList<>();
        r.addAll(userRequests);
        r.addAll(accountRequests);
        r.addAll(ATM.undoRequests);
        return r;
    }

    /**
     * Returns the request at a specified index in requests.
     *
     * @param i the index of requests
     * @return the request at index i
     */
    public Request getRequest(int i) {
        return getRequestList().get(i);
    }

    /**
     * Returns number of requests in the manager's notifications.
     *
     * @return the number of requests
     */
    public int getNumberOfRequests() { return getRequestList().size(); }

    /**
     * Returns a summary of the bank manager's requests.
     *
     * @return the summary of requests
     */
    public String getRequestSummary() {
        if (getRequestList().size() == 0) {
            return "You have no requests.";
        }

        StringBuilder summary = new StringBuilder("You have " + getNumberOfRequests() + " requests. \n");
        int i = 0;
        for (Request req : getRequestList()) {
            String line = i + ". " + req.toString() + "\n";
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
    public void addRequest(Request request) {
        if (request.getClass() == UserRequest.class) {
            userRequests.add((UserRequest) request);
        } else if (request.getClass() == AccountRequest.class) {
            accountRequests.add((AccountRequest) request);
        } else {
            ATM.undoRequests.add((UndoRequest) request);
        }
    }

    /**
     * Ignores a request at a specified index by deleting it from requests.
     *
     * @param i the index of requests
     */
    public void ignoreRequest(int i) {
        Request request = getRequest(i);
        if (request.getClass() == UserRequest.class) {
            userRequests.remove(request);
        } else if (request.getClass() == AccountRequest.class) {
            accountRequests.remove(request);
        } else {
            ATM.undoRequests.remove(request);
        }
    }

    /**
     * Completes a request at a specified index and deletes it from requests.
     *
     * @param i the index of requests
     */
    public void completeRequest(int i) {
        Request request = getRequest(i);
        request.resolveRequest();
        if (request.getClass() == UserRequest.class) {
            userRequests.remove(request);
        } else if (request.getClass() == AccountRequest.class) {
            accountRequests.remove(request);
        } else {
            ATM.undoRequests.remove(request);
        }
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (UserRequest req : userRequests) {
            s.append(req.toString()).append("\n");
        }
        for (AccountRequest req : accountRequests) {
            s.append(req.toString()).append("\n");
        }
        return s.toString();
    }
}
