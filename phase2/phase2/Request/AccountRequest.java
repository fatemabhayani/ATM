package phase2.Request;

import phase2.Display.ATM;
import phase2.People.User;
import java.util.Calendar;

/**
 * A request for a new bank account.
 */
public class AccountRequest extends Request {

    /**
     * The user making the request.
     */
    private User requester;

    /**
     * The type of account being requested.
     */
    private String accountType;

    /**
     * The currency code for the account.
     */
    private String currencyCode;

    /**
     * Instantiates a new account request.
     *
     * @param requester    the user that requests account creation
     * @param accountType  the account type
     * @param currencyCode the country code
     */
    public AccountRequest(User requester, String accountType, String currencyCode) {
        this.requester = requester;
        this.accountType = accountType;
        this.currencyCode = currencyCode;
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