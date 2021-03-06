package phase2.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import phase2.ATMTime;
import phase2.Accounts.*;
import phase2.CashMachine;
import phase2.Display.ATM;
import phase2.Request.*;
import phase2.Tradable.*;
import phase2.People.*;
import phase2.Transactions.*;

/**
 * The type Data reader.
 */
public class DataReader {

    public static void main(String[] args) {
        ATM.bankUsers = new ArrayList<>();
        ATM.undoRequests = new ArrayList<>();
        DataReader read = new DataReader();
        read.readAllUserData();
        read.readAllRequests();
        read.readATMData();


        for (User user : ATM.bankUsers) {
            System.out.println(user);
            for (Account account : user.getAccountList("sv")) {
                System.out.println(account.toString() + account.transactionString());
            }
            for (Account account : user.getAccountList("cq")) {
                System.out.println(account.toString() + account.transactionString());
            }
            for (Account account : user.getAccountList("lc")) {
                System.out.println(account.toString() + account.transactionString());
            }
            for (Account account : user.getAccountList("cc")) {
                System.out.println(account.toString() + account.transactionString());
            }
            for (Account account : user.getAccountList("cb")) {
                System.out.println(account.toString() + account.transactionString());
            }
        }
        for (UserRequest req: BankManager.getInstance().userRequests){
            System.out.println(req);
        }
        for (AccountRequest req: BankManager.getInstance().accountRequests){
            System.out.println(req);
        }
        for (UndoRequest req: ATM.undoRequests){
            System.out.println(req);
        }

    }

    private final String[] types = {"SAVINGS", "CHEQUING", "LINE OF CREDIT", "CREDIT CARD", "CASH BACK CARD"};


    /**
     * Initializes an instance of DataSaver.
     */

    public void readAll() {
        readATMData();
        readAllUserData();
        readAllRequests();
    }

    /** reads the atmdata.txt file
     *
     */
    private void readATMData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/atmdata.txt"))) {
            loadCash(reader.readLine());
            UserManager.accountNum = Integer.valueOf(reader.readLine());
            setTime(reader.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCash(String line) {
        String[] cash = line.split("/");
        int[] bills = new int[4];
        for (int i = 0; i < 4; i++) {
            bills[i] = Integer.valueOf(cash[i]);
        }
        CashMachine.getInstance().setBills(bills);
    }

    /**
     * sets the time for the ATM
     * @param line
     */
    private void setTime(String line) {
        String[] info = line.split(" ");
        int[] factors = new int[6];
        for (int i = 0; i < 6; i++) {
            factors[i] = Integer.valueOf(info[i]);
        }
        ATMTime.getInstance().setFactors(factors);
    }

    /**
     * reads the user data files
     */
    private void readAllUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/UserDataFiles/ListOfNames.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                readUserData(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * reads a specific user's data
     * @param name user's name
     */
    private void readUserData(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/UserDataFiles/" + name + ".txt"))) {
            String line = reader.readLine();
            User user = initializeUser(line);
            reader.readLine();
            line = reader.readLine();
            addAllAccounts(line, user, reader);
            ATM.bankUsers.add(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * intializes a new user
     * @param line
     * @return a new user
     */
    private User initializeUser(String line) {
        String[] info = line.split("\\.", 2);
        if (info[0].length() >= 6 && info[0].substring(0, 6).equals("teller")) {
            return new BankTeller(info[0], info[1]);
        } else {
            return new User(info[0], info[1]);
        }
    }

    /**
     * adds a new account
     * @param line
     * @param user the user that the account is under
     * @param type type of account
     */
    private void addAccount(String line, User user, String type) {
        Account account = initializeAccount(line, user, type);
        addAllTransactions(line, account);
        user.getAccountManager().add(account);
    }

    /**
     * adds the balances of the same types of accounts together
     * @param line
     * @param user
     * @param typeNum
     * @param reader
     * @throws IOException
     */
    private void addSameTypeAccounts(String line, User user, int typeNum, BufferedReader reader) throws IOException {
        if (typeNum < 4) {
            while (!line.equals(types[typeNum + 1])) {
                addAccount(line, user, types[typeNum]);
                line = reader.readLine();
            }
        } else {
            while (line != null) {
                addAccount(line, user, types[typeNum]);
                line = reader.readLine();
            }
        }

    }

    private void addAllAccounts(String line, User user, BufferedReader reader) throws IOException {
        for (int i = 0; i < 5; i++) {
            addSameTypeAccounts(line, user, i, reader);
            line = reader.readLine();
        }
    }

    /**
     * initialize a new account
     * @param line
     * @param user
     * @param type
     * @return
     */

    private Account initializeAccount(String line, User user, String type) {
        String[] info = line.split(" ");
        switch (type) {
            case "SAVINGS":
                return new Savings(getCorrectCalendar(info), user, info[2], info[0], info[1]);
            case "CREDIT CARD":
                return new CreditCard(getCorrectCalendar(info), user, info[2], info[0], info[1]);
            case "LINE OF CREDIT":
                return new LineOfCredit(getCorrectCalendar(info), user, info[2], info[0], info[1]);
            case "CASH BACK CARD":
                return new CashBackCard(getCorrectCalendar(info), user, info[2], info[0], info[1]);
            case "CHEQUING":
                return new Chequing(Boolean.valueOf(info[5]), getCorrectCalendar(info), user, info[2], info[0], info[1]);
            default:
                return new Savings(getCorrectCalendar(info), user, info[2], info[0], info[1]);
        }
    }

    /**
     * create a calendar
     * @param info
     * @return correct calendar
     */
    private GregorianCalendar getCorrectCalendar(String[] info) {
        String[] date = info[3].split("/");
        String[] time = info[3 + 1].split(":");
        return new GregorianCalendar(Integer.valueOf(date[0]), Integer.valueOf(date[1]) - 1, Integer.valueOf(date[2]),
                Integer.valueOf(time[0]), Integer.valueOf(time[1]), Integer.valueOf(time[2]));
    }

    private void addAllTransactions(String line, Account account) {
        String[] info = line.split("\t");
        for (int x = 1; x < info.length; x++) {
            readTransaction(info[x].split(" "), account);
        }
    }

    private void readTransaction(String[] info, Account account) {
        if (info[0].equals("T")) {
            readTransfer(info, account);
        } else {
            readOtherTransaction(info, account);
        }
    }

    private void readTransfer(String[] info, Account account) {
        ForeignCurrency cur = new ForeignCurrency(info[2], Double.valueOf(info[1]));
        Account from = UserManager.getUserAccount(Integer.valueOf(info[5]));
        Account to = UserManager.getUserAccount(Integer.valueOf(info[6]));
        account.addTransaction(new Transfer(cur, from, to, getCorrectCalendar(info)));
    }

    private void readOtherTransaction(String[] info, Account account) {
        ForeignCurrency cur = new ForeignCurrency(info[2], Double.valueOf(info[1]));
        switch (info[0]) {
            case "D":
                account.addTransaction(new Deposit(cur, account, getCorrectCalendar(info)));
                break;
            case "W":
                account.addTransaction(new Withdraw(cur, account, getCorrectCalendar(info)));
                break;
            case "B":
                account.addTransaction(new Bill(cur, account, getCorrectCalendar(info)));
        }
    }

    /**
     * reads the Requests.txt file
     */
    private void readAllRequests() {
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/Requests/Requests.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                readRequest(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * reads a specific request
     * @param line a line in the file
     */
    private void readRequest(String line) {
        String[] info = line.split(" ");
        switch (info[0]) {
            case "UNDO":
                addUndoRequest(info);
                break;
            case "USER":
                addUserRequest(info);
                break;
            case "ACCOUNT":
                addAccountRequest(info);
        }
    }

    /**
     * add a user request
     * @param info
     */
    private void addUserRequest(String[] info) {
        BankManager.getInstance().userRequests.add(new UserRequest(info[1], info[2]));
    }

    /**
     * add account request
     * @param info
     */
    private void addAccountRequest(String[] info) {
        User user = UserManager.getUser(info[1]);
        BankManager.getInstance().accountRequests.add(new AccountRequest(user, info[2], info[3]));
    }

    /**
     * add an undo request
     * @param info
     */
    private void addUndoRequest(String[] info) {
        User user = UserManager.getUser(info[1]);
        Account account = UserManager.getUserAccount(Integer.valueOf(info[2]));
        ATM.undoRequests.add(new UndoRequest(user, account, Integer.valueOf(info[3])));
    }

}