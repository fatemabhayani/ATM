package phase2.Request;

import phase2.Display.ATM;
import phase2.People.User;

import java.io.Serializable;
import java.util.Calendar;

/**
 * A request for a new bank account.
 */
public class AccountRequest extends Request implements Serializable {

    /**
     * The user making the request.
     */
    private User requester;

    /**
     * The type of account being requested
     */
    private String accountType;

    private String currencyCode;

    /**
     * Instantiates a new account request.
     *
     * @param requester the user that requests account creation
     * @param accountType the account type
     */
    public AccountRequest(User requester, String accountType, String currencyCode) {
        this.requester = requester;
        this.accountType = accountType;
        this.currencyCode = currencyCode;
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
     */
    public void resolveRequest() {
        Calendar time = ATM.clock.getCurrentTime();
        requester.getAccountManager().add(accountType, currencyCode, time);
    }

    @Override
    public String toString() {
        return requester.getUsername() + " wants to create a new " + accountType + " account.";
    }

}