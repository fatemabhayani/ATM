package phase2;

import java.io.Serializable;

/**
 * The User creation request, type of request
 */
public class UserRequest implements Request, Serializable {
    private String username;

    /**
     * Instantiates a new User request.
     *
     * @param username the username
     */
    public UserRequest(String username) {
        this.username = username;
    }

    public void resolveRequest() {
        UserManager.addUser(this.username);
    }

    @Override
    public String toString() {
        return "A new user, " + username + " wants to create an account.";
    }
}
