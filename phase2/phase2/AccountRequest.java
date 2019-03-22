package phase2;

import java.util.Calendar;

/**
 * A request for a new bank account.
 */
public class AccountRequest implements Request {

    /**
     * The user making the request.
     */
    private User requester;

    /**
     * The type of account being requested
     */
    private String accountType;

    /**
     * Instantiates a new account request.
     *
     * @param requester the user that requests account creation
     * @param accountType the account type
     */
    public AccountRequest(User requester, String accountType) {
        this.requester = requester;
        this.accountType = accountType;
    }

    /**
     * Returns the user that requested the account
     *
     * @return the requester
     */
    public User getRequester() {
        return requester;
    }

    /**
     * Returns a string version of the account type.
     *
     * @return the account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Resolves the request by creating the account.
     *
     */
    public void resolveRequest() {
        Calendar time = ATM.clock.getCurrentTime();
        User u = requester;
        switch (getAccountType()) {
            case ("lc"): u.lineCreditAccounts.add(new LineOfCredit(time));
                break;
            case ("cc"): u.creditCardAccounts.add(new CreditCard(time));
                break;
            case ("s"): u.savingsAccounts.add(new Savings(time));
                break;
            case ("c"):
                if (u.chequingAccounts.size() == 0) {
                    u.chequingAccounts.add(new Chequing(true, time));
                } else {
                    u.chequingAccounts.add(new Chequing(false, time));
                }
                break;
            case ("cb"): u.cashbackCardAccounts.add(new CashBackCard(time));
                break;
        }
    }

    @Override
    public String toString() {
        return requester.getUsername() + " wants to create a new " + accountType + " account.";
    }

}