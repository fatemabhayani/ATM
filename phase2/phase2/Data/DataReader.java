package phase2.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import phase2.Accounts.Account;
import phase2.Accounts.Savings;
import phase2.Display.ATM;
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
       DataReader read = new DataReader();
       read.readAllUserData();
       for (User user: ATM.bankUsers){
           System.out.println(user);
           for (Account account: user.getAccountList("sv")){
               Savings s = (Savings) account;
               System.out.println(s.toString() + s.transactionString());
           }
       }

    }


    /**
     * Initializes an instance of DataSaver.
     */

    private void readAllUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/UserDataFiles/ListOfNames.txt"))) {
            String line = reader.readLine();
            while (line != null){
                readUserData(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readUserData(String name){
        try (BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/UserDataFiles/" + name +".txt"))) {
            String line = reader.readLine();
            User user = initializeUser(line);
            reader.readLine();
            line = reader.readLine();
            while(!line.equals("CHEQUING ")){
                Savings s = initializeSavings(line, user);
                addAllTransactions(line, s);
                user.getAccountManager().add(s);
                line = reader.readLine();
            }
            ATM.bankUsers.add(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User initializeUser(String line) {
        String[] info = line.split("\\.", 2);
        return new User(info[0], info[1]);
    }

    private Savings initializeSavings(String line, User user){
        String[] info = line.split(" ");
        return new Savings(getCorrectCalendar(info), user, info[2], info[0], info[1]);
    }

    private GregorianCalendar getCorrectCalendar(String[] info){
        String[] date = info[3].split("/");
        String[] time = info[4].split(":");
        return new GregorianCalendar(Integer.valueOf(date[0]), Integer.valueOf(date[1]) - 1 ,Integer.valueOf(date[2]),
                Integer.valueOf(time[0]), Integer.valueOf(time[1]), Integer.valueOf(time[2]));
    }

    private void addAllTransactions(String line, Account account){
        String[] info = line.split("\t");
        for(int x = 1; x < info.length; x++){
            readTransaction(info[x].split(" "), account);
        }
    }

    private void readTransaction(String[] info, Account account){
        if (info[0].equals("T")){
            readTransfer(info, account);
        }
        else {
            readOtherTransaction(info, account);
        }
    }

    private void readTransfer(String[] info, Account account){
        ForeignCurrency cur = new ForeignCurrency(info[2], Double.valueOf(info[1]));
        Account from = UserManager.getUserAccount(Integer.valueOf(info[5]));
        Account to = UserManager.getUserAccount(Integer.valueOf(info[6]));
        account.addTransaction(new Transfer(cur, from, to, getCorrectCalendar(info)));
    }

    private void readOtherTransaction(String[] info, Account account){
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

}



