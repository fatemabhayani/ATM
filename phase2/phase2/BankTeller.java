package phase2;

public class BankTeller extends BankEmployee {
    // Start with fixed number (10?) of BankTellers?
    // username: teller1, ..., teller10
    // password: bestemployee1, ..., bestemployee10


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
