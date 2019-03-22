package phase2;

public class BankTeller extends BankEmployee {

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
    }

    public void createUserAccount(String username, String password) {
        u = new User(username, password);
        ATM.bankUsers.add(u);
    }

}
