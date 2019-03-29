package phase2.People;

import phase2.Display.ATM;
import phase2.Request.*;
import java.util.ArrayList;

/**
 * A bank manager for the ATM.
 */
public class BankManager extends BankEmployee {

    /**
     * The list of requests for the manager.
     */
    private ArrayList<Request> requests;

    /**
     * Creates a new bank manager.
     */
    public BankManager() {
        super("bankmanager", "bestboss");
        requests = new ArrayList<>();
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
        ATM.clock.setDate(year, month, date, hour, minute, second);
    }

    /**
     * Returns the request at a specified index in requests.
     *
     * @param i the index of requests
     * @return the request at index i
     */
    public Request getRequest(int i) {
        return requests.get(i);
    }

    /**
     * Returns number of requests in the manager's notifications.
     *
     * @return the number of requests
     */
    public int getNumberOfRequests() { return requests.size(); }

    /**
     * Returns a summary of the bank manager's requests.
     *
     * @return the summary of requests
     */
    public String getRequestSummary() {
        if (requests.size() == 0) {
            return "You have no requests.";
        }

        StringBuilder summary = new StringBuilder("You have " + getNumberOfRequests() + " requests. \n");
        int i = 0;
        for (Request req : requests) {
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
        requests.add(request);
    }

    /**
     * Ignores a request at a specified index by deleting it from requests.
     *
     * @param i the index of requests
     */
    public void ignoreRequest(int i) {
        requests.remove(i);
    }

    /**
     * Completes a request at a specified index and deletes it from requests.
     *
     * @param i the index of requests
     */
    public void completeRequest(int i) {
        requests.get(i).resolveRequest();
        requests.remove(i);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Request req : requests) {
            s.append(req.toString()).append("\n");
        }
        return s.toString();
    }
}
