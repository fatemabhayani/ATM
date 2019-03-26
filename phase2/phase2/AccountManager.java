package phase2;

import java.util.ArrayList;
import java.util.Locale;

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

    /**
     * Gets net balance of all asset accounts of a user in CAD.
     *
     * @return the net balance
     */
    private double getAssetBalance() {
        double assetBalance = 0;
        for (Chequing a : cq) {
            assetBalance += a.getBalance().convert(Locale.CANADA).getAmount();
        }
        for (Savings a : sv) {
            assetBalance += a.getBalance().convert(Locale.CANADA).getAmount();
        }
        return assetBalance;
    }

    /**
     * Gets net balance of all debt accounts of a user in CAD.
     *
     * @return the net balance
     */
    private double getDebtBalance() {
        double debtBalance = 0;
        for (Account a : cc) {
            debtBalance += a.getBalance().convert(Locale.CANADA).getAmount();
        }
        for (Account a : lc) {
            debtBalance += a.getBalance().convert(Locale.CANADA).getAmount();
        }
        for (Account a : cb){
            debtBalance += a.getBalance().convert(Locale.CANADA).getAmount();
        }
        return debtBalance;
    }

    /**
     * Gets net balance of all accounts of a user.
     *
     * @return the net balance
     */
    public double getTotalBalance() {
        return getAssetBalance() - getDebtBalance();
    }


}
