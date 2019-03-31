package phase2.Request;

import phase2.Accounts.Account;
import phase2.Transactions.Transaction;
import phase2.People.User;

/**
 * The Undo Transaction request, type of Request
 */
public class UndoRequest extends Request {
    private final User requester;
    private final Account account;
    private final int num;

    /**
     * Instantiates a new undo request.
     *
     * @param requester the user that requested the request
     * @param account   the account
     * @param num       the transaction number
     */
    public UndoRequest(User requester, Account account, int num) {
        this.requester = requester;
        this.account = account;
        this.num = num;
    }

    /**
     * Gets the requester.
     *
     * @return the user that requested the request
     */
    public User getRequester() {
        return requester;
    }

    /**
     * Gets the account.
     *
     * @return the account
     */
    public Account getAccountType() {
        return account;
    }

    /**
     * Resolves the request by undoing the transaction.
     */
    public void resolveRequest() {
        Transaction transaction = account.getTransactions().get(num);
        transaction.undoTransaction();
    }

    @Override
    public String toString() {
        return "UNDO " + requester.getUsername() + " " + account.getAccountNum() + " " + num;
    }
}

