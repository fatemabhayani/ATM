package phase2;

/**
 * The User creation request, type of request
 */
public class UserRequest extends Request {
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

