package phase2;

import java.io.Serializable;
import java.util.ArrayList;

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
     * The list of requests for this employee.
     */
    private ArrayList<Request> requests;

    /**
     * Creates a new bank employee.
     */
    public BankEmployee(String username, String password) {
        requests = new ArrayList<>();
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the request at a specified index in requests.
     *
     * @param i the index of requests
     * @return the request at index i
     */
    protected Request getRequest(int i) {
        return requests.get(i);
    }

    /**
     * Returns a summary of the bank manager's requests.
     *
     * @return the summary of requests
     */
    public String getRequestSummary() {
        if (requests.size() == 0) {
            return "You have no requests.";
        }
        StringBuilder summary = new StringBuilder();
        int i = 0;
        for (Request req : requests) {
            String line = i + req.toString() + "\n";
            summary.append(line);
            i++;
        }
        return summary.toString();
    }

    /**
     * Ignores a request at a specified index by deleting it from requests.
     *
     * @param i the index of requests
     */
    void ignoreRequest(int i) {
        requests.remove(i);
    }

    /**
     * Completes a request at a specified index and deletes it from requests.
     *
     * @param i the index of requests
     */
    void completeRequest(int i) {
        requests.get(i).resolveRequest();
        requests.remove(i);
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
     * Returns the username of the bank manager.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the bank manager.
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

}
