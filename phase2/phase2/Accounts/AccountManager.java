package phase2.Accounts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import phase2.People.*;
import phase2.Transactions.*;

/**
 * Manages a user's list of accounts.
 */
public class AccountManager {
    /**
     * Line of credit accounts.
     */
    private ArrayList<LineOfCredit> lc;

    private ArrayList<InvestmentAccount> in;

    /**
     * Credit card accounts.
     */
    private ArrayList<CreditCard> cc;

    /**
     * Chequing accounts.
     */
    private ArrayList<Chequing> cq;

    /**
     * Savings accounts.
     */
    private ArrayList<Savings> sv;

    /**
     * Cash back card accounts.
     */
    private ArrayList<CashBackCard> cb;

    /**
     * AccountManager owner.
     */
    private final User owner;

    /**
     * Instantiates a new account manager.
     */
    public AccountManager(User u) {
        lc = new ArrayList<>();
        cc = new ArrayList<>();
        cq = new ArrayList<>();
        sv = new ArrayList<>();
        cb = new ArrayList<>();
        in = new ArrayList<>();
        owner = u;
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
     * Gets whether or not the account number belongs to an account in this AccountManager.
     *
     * @return whether the account with num is in this AccountManager
     */
    public boolean hasAccount(int num) {
        for (LineOfCredit a : lc) {
            if (a.getAccountNum() == num) {
                return true;
            }
        }
        for (InvestmentAccount a : in) {
            if (a.getAccountNum() == num) {
                return true;
            }
        }
        for (CreditCard a : cc) {
            if (a.getAccountNum() == num) {
                return true;
            }
        }
        for (Chequing a : cq) {
            if (a.getAccountNum() == num) {
                return true;
            }
        }
        for (Savings a : sv) {
            if (a.getAccountNum() == num) {
                return true;
            }
        }
        for (CashBackCard a : cb) {
            if (a.getAccountNum() == num) {
                return true;
            }
        }
        return false;
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
            case "in": return in;
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
        for (InvestmentAccount a : in) {
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
        Collections.sort(t);
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
     * Returns the account summary.
     *
     * @return the string that summarizes the accounts of user.
     */
    public String accountSummary(String type) {
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case("lc"):
                for (LineOfCredit a : lc) {
                    sb.append("Line of Credit account: ").append(a.accountNum).append("\n");
                }
                return sb.toString();
            case("cc"):
                for (CreditCard a : cc) {
                    sb.append("Credit Card account: ").append(a.accountNum).append("\n");
                }
                return sb.toString();
            case("cq"):
                for (Chequing a : cq) {
                    sb.append("Chequing account: ").append(a.accountNum).append("\n");
                }
                return sb.toString();
            case("cb"):
                for (CashBackCard a : cb)
                    sb.append("Cash Back Card account: ").append(a.accountNum).append("\n");
                return sb.toString();
            case("sv"):
                for (Savings a : sv) {
                    sb.append("Savings account: ").append(a.accountNum).append("\n");
                }
                return sb.toString();
        }
        return "";
    }

    /**
     * Adds a new account.
     *
     * @param accountType the account type
     * @param curr        the country associated with the account
     * @param time        the time of creation
     */
    public void add(String accountType, String curr, Calendar time) {
        int num = UserManager.getNextAccountNum();
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
        } else if (account.getClass() == InvestmentAccount.class) {
            in.add((InvestmentAccount) account);
        }else if (account.getClass() == Savings.class) {
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
        for (InvestmentAccount a : in) {
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
