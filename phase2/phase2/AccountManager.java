package phase2;

import java.util.ArrayList;

public class AccountManager {
    /**
     * Line of credit accounts.
     */
    ArrayList<LineOfCredit> lc;

    /**
     * Credit card accounts.
     */
    ArrayList<CreditCard> cc;

    /**
     * Chequing accounts.
     */
    ArrayList<Chequing> cq;

    /**
     * Savings accounts.
     */
    ArrayList<Savings> sv;

    /**
     * Cash back card accounts.
     */
    ArrayList<CashBackCard> cb;

    /**
     * Instantiates a new account manager.
     */
    public AccountManager() {
        lc = new ArrayList<>();
        cc = new ArrayList<>();
        cq = new ArrayList<>();
        sv = new ArrayList<>();
        cb = new ArrayList<>();
    }

    /**
     * Instantiates a new account manager.
     *
     * @param lc line of credit accounts
     * @param cc credit card accounts
     * @param cq line of credit accounts
     */
    public AccountManager(ArrayList<LineOfCredit> lc, ArrayList<CreditCard> cc, ArrayList<Chequing> cq,
                          ArrayList<Savings> sv, ArrayList<CashBackCard> cb) {
        this.lc = lc;
        this.cc = cc;
        this.cq = cq;
        this.sv = sv;
        this.cb = cb;
    }


}
