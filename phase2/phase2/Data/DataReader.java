package phase2.Data;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import phase2.Display.ATM;
import phase2.ForeignCurrency;
import phase2.People.*;
import phase2.Accounts.*;

/**
 * The type Data reader.
 */
public class DataReader {

    /**
     * Initializes an instance of DataSaver.
     */
    private DataReader() {}

    /**
     * Read the data and enters it into the ATM.
     */
    public static void readData() {
        try {
            readATMData();
            readUserData();
            readTransactionData();
            readEmployeeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read atmdata.txt and enters it into the ATM.
     */
    private static void readATMData() {
        String s;
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/atmdata.txt"))) {
            reader.readLine();
            s = reader.readLine();
            String[] time = s.split("\\s|/|:");
            ATM.clock.setDate(Integer.valueOf(time[0]), Integer.valueOf(time[1]) - 1, Integer.valueOf(time[2]),
                    Integer.valueOf(time[3]), Integer.valueOf(time[4]), Integer.valueOf(time[5]));

            reader.readLine();
            s = reader.readLine();
            String[] b = s.split("/");
            for (int i = 0; i < 4; i++) {
                ATM.c.increaseBills(i, Integer.valueOf(b[i]));
            }

            reader.readLine();
            s = reader.readLine();
            UserManager.accountNum = Integer.valueOf(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read userdata.txt and enters it into the ATM.
     */
    private static void readUserData() {
        String s;
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/userdata.txt"))) {
            reader.readLine(); // ATM BANK USERS
            s = reader.readLine(); // USER
            while (s != null) {
                s = reader.readLine(); // username.password
                while (s != null && !s.equals("USER")) {
                    String[] userInfo = s.split(".");
                    User u = new User(userInfo[0], userInfo[1]);
                    ATM.bankUsers.add(u);
                    reader.readLine(); // SAVINGS
                    s = reader.readLine(); // savings account 1 info
                    while (!s.equals("CHEQUING")) {
                        Savings a = makeSavings(s, u);
                        u.getAccountManager().add(a);
                        s = reader.readLine();
                    }
                    s = reader.readLine(); // chequing account 1 info
                    while (!s.equals("CASH BACK")) {
                        Chequing a = makeChequing(s, u);
                        u.getAccountManager().add(a);
                        s = reader.readLine();
                    }
                    s = reader.readLine(); // cash back account 1 info
                    while (!s.equals("CREDIT CARD")) {
                        CashBackCard a = makeCashBack(s, u);
                        u.getAccountManager().add(a);
                        s = reader.readLine();
                    }
                    s = reader.readLine(); // credit card account 1 info
                    while (!s.equals("LINE OF CREDIT")) {
                        CreditCard a = makeCreditCard(s, u);
                        u.getAccountManager().add(a);
                        s = reader.readLine();
                    }
                    s = reader.readLine(); // line of credit account 1 info
                    while (s != null && !s.equals("USER")) {
                        LineOfCredit a = makeLineOfCredit(s, u);
                        u.getAccountManager().add(a);
                        s = reader.readLine();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read transactiondata.txt and enters it into the ATM.
     */
    private static void readTransactionData() {
        String s;
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/transactiondata.txt"))) {
            reader.readLine();
            s = reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read employeedata.txt and enters it into the ATM.
     */
    private static void readEmployeeData() {
        String s;
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/employeedata.txt"))) {
            reader.readLine();
            s = reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Savings account represented by s.
     *
     * @param s the string
     * @param u the user owner
     * @return a savings account
     */
    private static Savings makeSavings(String s, User u) {
        String[] info = s.split("\\s|/|:");
        int num = Integer.valueOf(info[0]);
        if (UserManager.getUserAccount(num) != null) {
            Account acc = UserManager.getUserAccount(num);
            acc.setNewOwner(u);
            return (Savings) acc;
        } else {
            Calendar date = new GregorianCalendar(Integer.valueOf(info[3]), Integer.valueOf(info[4]) - 1,
                    Integer.valueOf(info[5]), Integer.valueOf(info[6]), Integer.valueOf(info[7]), Integer.valueOf(info[8]));
            Savings acc = new Savings(date, u, info[2], num);
            acc.setBalance(new ForeignCurrency(info[2], Double.valueOf(info[1])));
            return acc;
        }
    }

    /**
     * Returns the Chequing account represented by s.
     *
     * @param s the string
     * @param u the user owner
     * @return a chequing account
     */
    private static Chequing makeChequing(String s, User u) {
        String[] info = s.split("\\s|/|:");
        int num = Integer.valueOf(info[0]);
        boolean isPrimary = new Boolean(info[9]);
        if (UserManager.getUserAccount(num) != null) {
            Account acc = UserManager.getUserAccount(num);
            acc.setNewOwner(u);
            return (Chequing) acc;
        } else {
            Calendar date = new GregorianCalendar(Integer.valueOf(info[3]), Integer.valueOf(info[4]) - 1,
                    Integer.valueOf(info[5]), Integer.valueOf(info[6]), Integer.valueOf(info[7]), Integer.valueOf(info[8]));
            Chequing acc = new Chequing(isPrimary, date, u, info[2], num);
            acc.setBalance(new ForeignCurrency(info[2], Double.valueOf(info[1])));
            return acc;
        }
    }

    /**
     * Returns the Credit Card account represented by s.
     *
     * @param s the string
     * @param u the user owner
     * @return a credit card account
     */
    private static CreditCard makeCreditCard(String s, User u) {
        String[] info = s.split("\\s|/|:");
        int num = Integer.valueOf(info[0]);
        if (UserManager.getUserAccount(num) != null) {
            Account acc = UserManager.getUserAccount(num);
            acc.setNewOwner(u);
            return (CreditCard) acc;
        } else {
            Calendar date = new GregorianCalendar(Integer.valueOf(info[3]), Integer.valueOf(info[4]) - 1,
                    Integer.valueOf(info[5]), Integer.valueOf(info[6]), Integer.valueOf(info[7]), Integer.valueOf(info[8]));
            CreditCard acc = new CreditCard(date, u, info[2], num);
            double balance = Double.valueOf(info[1]);
            acc.setBalance(new ForeignCurrency(info[2], balance));
            if (balance > 0) {
                acc.decreaseCreditLimit(new ForeignCurrency(info[2], balance));
            } else {
                acc.increaseCreditLimit(new ForeignCurrency(info[2], -balance));
            }
            return acc;
        }
    }

    /**
     * Returns the Line of Credit account represented by s.
     *
     * @param s the string
     * @param u the user owner
     * @return a line of credit account
     */
    private static LineOfCredit makeLineOfCredit(String s, User u) {
        String[] info = s.split("\\s|/|:");
        int num = Integer.valueOf(info[0]);
        if (UserManager.getUserAccount(num) != null) {
            Account acc = UserManager.getUserAccount(num);
            acc.setNewOwner(u);
            return (LineOfCredit) acc;
        } else {
            Calendar date = new GregorianCalendar(Integer.valueOf(info[3]), Integer.valueOf(info[4]) - 1,
                    Integer.valueOf(info[5]), Integer.valueOf(info[6]), Integer.valueOf(info[7]), Integer.valueOf(info[8]));
            LineOfCredit acc = new LineOfCredit(date, u, info[2], num);
            double balance = Double.valueOf(info[1]);
            acc.setBalance(new ForeignCurrency(info[2], balance));
            if (balance > 0) {
                acc.decreaseCreditLimit(new ForeignCurrency(info[2], balance));
            } else {
                acc.increaseCreditLimit(new ForeignCurrency(info[2], -balance));
            }
            return acc;
        }
    }

    /**
     * Returns the Cash Back Card account represented by s.
     *
     * @param s the string
     * @param u the user owner
     * @return a cash back card account
     */
    private static CashBackCard makeCashBack(String s, User u) {
        String[] info = s.split("\\s|/|:");
        int num = Integer.valueOf(info[0]);
        if (UserManager.getUserAccount(num) != null) {
            Account acc = UserManager.getUserAccount(num);
            acc.setNewOwner(u);
            return (CashBackCard) acc;
        } else {
            Calendar date = new GregorianCalendar(Integer.valueOf(info[3]), Integer.valueOf(info[4]) - 1,
                    Integer.valueOf(info[5]), Integer.valueOf(info[6]), Integer.valueOf(info[7]), Integer.valueOf(info[8]));
            CashBackCard acc = new CashBackCard(date, u, info[2], num);
            double balance = Double.valueOf(info[1]);
            acc.setBalance(new ForeignCurrency(info[2], balance));
            if (balance > 0) {
                acc.decreaseCreditLimit(new ForeignCurrency(info[2], balance));
            } else {
                acc.increaseCreditLimit(new ForeignCurrency(info[2], -balance));
            }
            return acc;
        }
    }

}