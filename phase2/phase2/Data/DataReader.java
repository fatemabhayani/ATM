package phase2.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import phase2.Accounts.*;
import phase2.CashMachine;
import phase2.Display.ATM;
import phase2.Request.AccountRequest;
import phase2.Request.UndoRequest;
import phase2.Request.UserRequest;
import phase2.Tradable.*;
import phase2.People.*;
import phase2.Transactions.Bill;
import phase2.Transactions.Deposit;
import phase2.Transactions.Transfer;
import phase2.Transactions.Withdraw;

/**
 * The type Data reader.
 */
public class DataReader {

    public static void main(String[] args) {
        ATM.bankUsers = new ArrayList<>();
        ATM.b.userRequests = new ArrayList<>();
        ATM.b.accountRequests = new ArrayList<>();
        ATM.undoRequests = new ArrayList<>();
        DataReader read = new DataReader();
        read.readAllUserData();
        read.readAllRequests();
        read.readATMData();
        int x = 0;


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
        for (UserRequest req: ATM.b.userRequests){
            System.out.println(req);
        }
        for (AccountRequest req: ATM.b.accountRequests){
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

    private void readATMData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/atmdata.txt"))) {
            String line = reader.readLine();
            loadCash(line);
            UserManager.accountNum = Integer.valueOf(reader.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCash(String line){
        String[] cash = line.split("/");
        int[] bills = new int[4];
        for (int i = 0; i < 4; i++) {
            bills[i] = Integer.valueOf(cash[i]);
        }
        ATM.c = new CashMachine(bills);
    }

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

    private User initializeUser(String line) {
        String[] info = line.split("\\.", 2);
        return new User(info[0], info[1]);
    }

    private void addAccount(String line, User user, String type) {
        Account account = initializeAccount(line, user, type);
        addAllTransactions(line, account);
        user.getAccountManager().add(account);
    }

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


    private Account initializeAccount(String line, User user, String type) {
        String[] info = line.split(" ");
        switch (type) {
            case "SAVINGS":
                return new Savings(getCorrectCalendar(info, 3), user, info[2], info[0], info[1]);
            case "CREDIT CARD":
                return new CreditCard(getCorrectCalendar(info,3), user, info[2], info[0], info[1]);
            case "LINE OF CREDIT":
                return new LineOfCredit(getCorrectCalendar(info,3), user, info[2], info[0], info[1]);
            case "CASH BACK CARD":
                return new CashBackCard(getCorrectCalendar(info,3), user, info[2], info[0], info[1]);
            case "CHEQUING":
                return new Chequing(Boolean.valueOf(info[5]), getCorrectCalendar(info,3), user, info[2], info[0], info[1]);
            default:
                return new Savings(getCorrectCalendar(info,3), user, info[2], info[0], info[1]);
        }
    }

    private GregorianCalendar getCorrectCalendar(String[] info, int index) {
        String[] date = info[index].split("/");
        String[] time = info[index + 1].split(":");
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
        account.addTransaction(new Transfer(cur, from, to, getCorrectCalendar(info,3)));
    }

    private void readOtherTransaction(String[] info, Account account) {
        ForeignCurrency cur = new ForeignCurrency(info[2], Double.valueOf(info[1]));
        switch (info[0]) {
            case "D":
                account.addTransaction(new Deposit(cur, account, getCorrectCalendar(info,3)));
                break;
            case "W":
                account.addTransaction(new Withdraw(cur, account, getCorrectCalendar(info,3)));
                break;
            case "B":
                account.addTransaction(new Bill(cur, account, getCorrectCalendar(info,3)));
        }
    }

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

    private void addUserRequest(String[] info) {
        ATM.b.userRequests.add(new UserRequest(info[1], info[2]));
    }

    private void addAccountRequest(String[] info) {
        User user = UserManager.getUser(info[1]);
        ATM.b.accountRequests.add(new AccountRequest(user, info[2], info[3]));
    }

    private void addUndoRequest(String[] info) {
        User user = UserManager.getUser(info[1]);
        Account account = UserManager.getUserAccount(Integer.valueOf(info[2]));
        ATM.undoRequests.add(new UndoRequest(user, account, Integer.valueOf(info[3])));
    }

}



