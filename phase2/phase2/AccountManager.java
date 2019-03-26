package phase2;

import java.util.ArrayList;
import java.util.Calendar;
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
     * Gets net balance of all accounts of a user in CAD.
     *
     * @return the net balance
     */
    public double getTotalBalance() {
        return getAssetBalance() - getDebtBalance();
    }

    /**
     * Gets the list of accounts of type s.
     *
     * @param s the type of account
     * @return the list of accounts
     */
    public ArrayList getAccount(String s) {
        switch (s) {
            case "lc": return lc;
            case "cc": return cc;
            case "cq": return cq;
            case "sv": return sv;
            case "cb": return cb;
            default:
                System.out.println("No accounts of that type!");
                return new ArrayList();
        }
    }

    /**
     * Gets the list of all past transactions.
     *
     * @return an ordered list of past transactions
     */
    public ArrayList<Transaction> getTransactions() {


        return new ArrayList<>();
    }

    /**
     * Returns the balance summary.
     *
     * @return the string that summarizes the balance in each account of user.
     */
    public String balanceSummary() {
        StringBuilder sb = new StringBuilder();
        for (LineOfCredit a : lc) {
            sb.append("Line of Credit account: ").append(a.getBalance().toString()).append("\n");
        }
        for (CreditCard a : cc) {
            sb.append("Credit Card account: ").append(a.getBalance().toString()).append("\n");
        }
        for (Savings a : sv) {
            sb.append("Savings account: ").append(a.getBalance().toString()).append("\n");
        }
        for (Chequing a : cq) {
            sb.append("Chequing account: ").append(a.getBalance().toString()).append("\n");
        }
        for (CashBackCard a : cb)
            sb.append("Cash Back Card account: ").append(a.getBalance().toString()).append("\n");
        String s = sb.toString();
        s = s + ("Total balance in Canadian dollars: " + getTotalBalance());
        return s;
    }

    /**
     * Adds a new account.
     *
     * @param accountType the account type
     * @param locale the country associated with the account
     * @param time the time of creation
     */
    public void add(String accountType, Locale locale, Calendar time) {
        switch (accountType) {
            case ("lc"): lc.add(new LineOfCredit(time, locale)); break;
            case ("cc"): cc.add(new CreditCard(time, locale)); break;
            case ("sv"): sv.add(new Savings(time, locale)); break;
            case ("cq"):
                if (cq.size() == 0) { cq.add(new Chequing(true, time, locale));
                } else { cq.add(new Chequing(false, time, locale)); } break;
            case ("cb"): cb.add(new CashBackCard(time, locale)); break;
        }
    }
}
