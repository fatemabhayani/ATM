package phase2.Request;

import phase2.People.UserManager;

/**
 * A request for a new user account.
 */
public class UserRequest extends Request {
    /**
     * The username for the new user.
     */
    private final String username;

    /**
     * The username for the new user.
     */
    private final String password;

    /**
     * Instantiates a new ser request.
     *
     * @param username the username
     * @param password the password
     */
    public UserRequest(String username, String password) {
        this.password = password;
        this.username = username;
    }

    /**
     * Resolves the request by making the user.
     */
    public void resolveRequest() {
        UserManager.addUser(username, password);
    }

    @Override
    public String toString() {
        return "USER " + username + " " + password;
    }
}

