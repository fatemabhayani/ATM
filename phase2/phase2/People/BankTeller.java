package phase2.People;

import phase2.Display.ATM;
import phase2.People.BankEmployee;
import phase2.People.User;
import phase2.Request.*;

import java.util.ArrayList;

public class BankTeller extends BankEmployee {
    // Start with fixed number (10?) of BankTellers?
    // username: teller1, ..., teller10
    // password: bestemployee1, ..., bestemployee10

    /**
     * The list of requests for this employee.
     */
    private ArrayList<UndoRequest> requests;

    /**
     * The user account of this teller.
     */
    private User u;

    /**
     * Creates a new bank teller.
     */
    public BankTeller(String username, String password) {
        super(username, password);
        u = null;
        requests = new ArrayList<>();
    }

    public void createUserAccount(String username, String password) {
        u = new User(username, password);
        ATM.bankUsers.add(u);
    }

    /**
     * Returns the request at a specified index in requests.
     *
     * @param i the index of requests
     * @return the request at index i
     */
    protected UndoRequest getRequest(int i) {
        return requests.get(i);
    }

    private int getNumberOfRequests(){return requests.size();}

    /**
     * Returns a summary of the bank manager's requests.
     *
     * @return the summary of requests
     */
    public String getRequestSummary() {
        if (requests.size() == 0) {
            return "You have no requests.";
        }

        StringBuilder summary = new StringBuilder("You have " + getNumberOfRequests() + " requests.");
        int i = 0;
        for (Request req : requests) {
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
        requests.add(request);
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

}