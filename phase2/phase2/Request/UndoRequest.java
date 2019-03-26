package phase2.Request;

import phase2.Accounts.Account;
import phase2.Transactions.Transaction;
import phase2.People.User;

import java.io.Serializable;

/**
 * The Undo Transaction request, type of Request
 */
public class UndoRequest extends Request implements Serializable {
    private User requester;
    private Account account;
    private int num;

    /**
     * Instantiates a new Undo request.
     *
     * @param requester the user that requested the request
     * @param account   the account
     */
    public UndoRequest(User requester, Account account, int num){
        this.requester = requester;
        this.account = account;
        this.num = num;
    }

    /**
     * Gets requester.
     *
     * @return the user that requested the request
     */
    public User getRequester() {
        return requester;
    }

    /**
     * Gets account type.
     *
     * @return the account type
     */
    public Account getAccountType() {
        return account;
    }

    @Override
    public String toString() {
        return requester.getUsername() + " wants to undo their last transaction on " + account.toString();
    }

    @Override
    public void resolveRequest() {
        Transaction transaction = account.getTransactions().get(num);
        transaction.undoTransaction();
    }
}

