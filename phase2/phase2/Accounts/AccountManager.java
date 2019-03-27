package phase2.Accounts;

import java.util.ArrayList;
import java.util.Calendar;
import phase2.People.*;
import phase2.Transactions.*;

/**
 * Manages a user's list of accounts.
 */
public class AccountManager {
    /**
     * Line of credit accounts.
     */
    private final ArrayList<LineOfCredit> lc;

    /**
     * Credit card accounts.
     */
    private final ArrayList<CreditCard> cc;

    /**
     * Chequing accounts.
     */
    private final ArrayList<Chequing> cq;

    /**
     * Savings accounts.
     */
    private final ArrayList<Savings> sv;

    /**
     * Cash back card accounts.
     */
    private final ArrayList<CashBackCard> cb;

    /**
     * AccountManager owner.
     */
    private User owner;

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
     * Gets net balance of all asset accounts of a user in CAD.
     *
     * @return the net balance
     */
    private double getAssetBalance() {
        double assetBalance = 0;
        for (Chequing a : cq) {
            assetBalance += a.getBalance().convert("CAD").getAmount();
        }
        for (Savings a : sv) {
            assetBalance += a.getBalance().convert("CAD").getAmount();
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
            debtBalance += a.getBalance().convert("CAD").getAmount();
        }
        for (Account a : lc) {
            debtBalance += a.getBalance().convert("CAD").getAmount();
        }
        for (Account a : cb){
            debtBalance += a.getBalance().convert("CAD").getAmount();
        }
        return debtBalance;
    }

    /**
     * Gets net balance of all accounts of a user in CAD.
     *
     * @return the net balance
     */
    private double getTotalBalance() {
        return getAssetBalance() - getDebtBalance();
    }

    /**
     * Gets the list of accounts of type s.
     *
     * @param s the type of account
     * @return the list of accounts
     */
    public ArrayList getAccountList(String s) {
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
        ArrayList<Transaction> t = new ArrayList<>();
        for (LineOfCredit a : lc) {
            t.addAll(a.getTransactions());
        }
        for (CreditCard a : cc) {
            t.addAll(a.getTransactions());
        }
        for (Chequing a : cq) {
            t.addAll(a.getTransactions());
        }
        for (Savings a : sv) {
            t.addAll(a.getTransactions());
        }
        for (CashBackCard a : cb) {
            t.addAll(a.getTransactions());
        }

        t.sort(new TransactionComparator());
        return t;
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
     * @param curr        the country associated with the account
     * @param time        the time of creation
     */
    public void add(String accountType, String curr, Calendar time) {
        int num = lc.size() + cc.size() + cq.size() + sv.size() + cb.size() + 1;
        switch (accountType) {
            case ("lc"): lc.add(new LineOfCredit(time, owner, curr, num)); break;
            case ("cc"): cc.add(new CreditCard(time, owner, curr, num)); break;
            case ("sv"): sv.add(new Savings(time, owner, curr, num)); break;
            case ("cq"):
                if (cq.size() == 0) { cq.add(new Chequing(true, time, owner, curr, num));
                } else { cq.add(new Chequing(false, time, owner, curr, num)); } break;
            case ("cb"): cb.add(new CashBackCard(time, owner, curr, num)); break;
        }
    }

    /**
     * Adds a previously made account.
     *
     * @param account the account
     */
    public void add(Account account) {
        if (account.getClass() == LineOfCredit.class) {
            lc.add((LineOfCredit) account);
        } else if (account.getClass() == CreditCard.class) {
            cc.add((CreditCard) account);
        } else if (account.getClass() == Savings.class) {
            sv.add((Savings) account);
        } else if (account.getClass() == Chequing.class) {
            cq.add((Chequing) account);
        } else {
            cb.add((CashBackCard) account);
        }
    }

    /**
     * Gets the account given its unique number.
     *
     * @param num the account number
     * @return the account
     */
    public Account getAccount(int num) {
        for (LineOfCredit a : lc) {
            if (a.accountNum == num) { return a; }
        }
        for (CreditCard a : cc) {
            if (a.accountNum == num) { return a; }
        }
        for (Savings a : sv) {
            if (a.accountNum == num) { return a; }
        }
        for (Chequing a : cq) {
            if (a.accountNum == num) { return a; }
        }
        for (CashBackCard a : cb) {
            if (a.accountNum == num) { return a; }
        }

        System.out.println("That is not a valid account number!");
        return null;
    }
}
