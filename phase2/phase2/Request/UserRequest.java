package phase2.Request;

import phase2.Request;
import phase2.People.UserManager;

import java.io.Serializable;

/**
 * The User creation request, type of request
 */
public class UserRequest extends Request implements Serializable {
    private String username;

    /**
     * Instantiates a new User request.
     *
     * @param username the username
     */
    public UserRequest(String username) {
        this.username = username;
    }

    public void resolveRequest(String password) {
        UserManager.addUser(this.username, password);
    }

    @Override
    public String toString() {
        return "A new user, " + username + " wants to create an account.";
    }
}

