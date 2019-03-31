package phase2.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import phase2.Display.ATM;
import phase2.Tradable.*;
import phase2.People.*;
import phase2.Accounts.*;
import phase2.Transactions.*;
import phase2.Request.*;

/**
 * The type Data saver.
 */
public class DataSaver {

    public static void main(String[] args) {

        User user = new User("yes", "yes");
        Savings sv = new Savings(new GregorianCalendar(), user, "CAD", 0);
        Chequing cq = new Chequing(true,new GregorianCalendar(), user, "CAD", 1);
        CreditCard cc = new CreditCard(new GregorianCalendar(), user, "CAD", 2);
        LineOfCredit lc = new LineOfCredit(new GregorianCalendar(), user, "CAD", 3);
        CashBackCard cb = new CashBackCard(new GregorianCalendar(), user, "CAD", 4);
        user.getAccountManager().add(sv);
        user.getAccountManager().add(cq);
        user.getAccountManager().add(cc);
        user.getAccountManager().add(lc);
        user.getAccountManager().add(cb);
        Deposit b = new Deposit(new ForeignCurrency("CAD", 50), sv, new GregorianCalendar());
        user.makeTransaction(b);
        Deposit b1 = new Deposit(new ForeignCurrency("CAD", 50), cq, new GregorianCalendar());
        user.makeTransaction(b1);
        Deposit b2 = new Deposit(new ForeignCurrency("CAD", 50), cc, new GregorianCalendar());
        user.makeTransaction(b2);
        Deposit b3 = new Deposit(new ForeignCurrency("CAD", 50), lc, new GregorianCalendar());
        user.makeTransaction(b3);
        Deposit b4 = new Deposit(new ForeignCurrency("CAD", 50), cb, new GregorianCalendar());
        user.makeTransaction(b4);
        DataSaver d = new DataSaver();
        ATM.bankUsers = new ArrayList<>();
        ATM.bankUsers.add(user);
        d.writeAllUsers();

        UserRequest r1 = new UserRequest("newperson", "secretpw");
        ATM.b.userRequests.add(r1);
        AccountRequest r2 = new AccountRequest(user, "cc", "USD");
        ATM.b.accountRequests.add(r2);
        UndoRequest r3 = new UndoRequest(user, sv, 0);
        ATM.undoRequests.add(r3);

        d.writeAllRequests();

    }

    /**
     * Writes the time and cash machine contents into atmdata.txt.
     */
    private void writeATMData() {
        try (FileWriter writer = new FileWriter("phase2/phase2/Data/atmdata.txt")) {
            writer.write("ATM CLOCK \n");
            writer.write(ATM.clock.toString() + "\n");
            writer.write("ATM CASH MACHINE \n");
            writer.write(ATM.c.toString()+"\n");
            writer.write("ATM ACCOUNT NUMBER \n");
            writer.write(((Integer)UserManager.accountNum).toString() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeAllUsers(){
        try{
            FileWriter writer = new FileWriter("phase2/phase2/Data/UserDataFiles/ListOfNames.txt");
            writer.close();
        } catch (Exception e){
            e.getStackTrace();
        }
        for(User user: ATM.bankUsers){
            writeUserData(user);
        }
    }


    private void writeUserData(User user){
        appendName(user);
        try (FileWriter writer = new FileWriter("phase2/phase2/Data/UserDataFiles/" + user.getUsername() + ".txt")){
            writer.write(user.toString() +"\n");
            writeAllSavings(writer, user);
            writeAllChequing(writer, user);
            writeAllLineOfCredit(writer, user);
            writeAllCreditCard(writer, user);
            writeAllCashBackCard(writer, user);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void appendName(User user){
        try (FileWriter writer = new FileWriter("phase2/phase2/Data/UserDataFiles/ListOfNames.txt", true)){
            writer.write(user.getUsername() + "\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void writeSavingAccount(FileWriter writer, Savings s) throws IOException {
        writer.write(s.toString() +" ");
        writer.write(s.transactionString() + "\n");
    }

    private void writeAllSavings(FileWriter writer, User user) throws IOException{
        writer.write("SAVINGS\n");
        for(Account account: user.getAccountList("sv")){
            Savings s = (Savings) account;
            writeSavingAccount(writer, s);
        }
    }

    private void writeChequingAccount(FileWriter writer, Chequing c) throws IOException {
        writer.write(c.toString() +" ");
        writer.write(c.transactionString() + "\n");
    }

    private void writeAllChequing(FileWriter writer, User user) throws IOException{
        writer.write("CHEQUING\n");
        for(Account account: user.getAccountList("cq")){
            Chequing c = (Chequing) account;
            writeChequingAccount(writer, c);
        }
    }

    private void writeCreditCardAccount(FileWriter writer, CreditCard c) throws IOException {
        writer.write(c.toString() + " ");
        writer.write(c.transactionString() + "\n");
    }

    private void writeAllCreditCard(FileWriter writer, User user) throws IOException{
        writer.write("CREDIT CARD\n");
        for(Account account: user.getAccountList("cc")){
            CreditCard c = (CreditCard) account;
            writeCreditCardAccount(writer, c);
        }
    }

    private void writeLineOfCreditAccount(FileWriter writer, LineOfCredit c) throws IOException {
        writer.write(c.toString() + " ");
        writer.write(c.transactionString() + "\n");
    }


    private void writeAllLineOfCredit(FileWriter writer, User user) throws IOException{
        writer.write("LINE OF CREDIT\n");
        for(Account account: user.getAccountList("lc")){
            LineOfCredit c = (LineOfCredit) account;
            writeLineOfCreditAccount(writer, c);
        }
    }

    private void writeCashBackCardAccount(FileWriter writer, CashBackCard c) throws IOException {
        writer.write(c.toString() + " ");
        writer.write(c.transactionString() + "\n");
    }


    private void writeAllCashBackCard(FileWriter writer, User user) throws IOException{
        writer.write("CASH BACK CARD\n");
        for(Account account: user.getAccountList("cb")){
            CashBackCard c = (CashBackCard) account;
            writeCashBackCardAccount(writer, c);
        }
    }

    private void writeAllRequests(){
        try {FileWriter writer = new FileWriter("phase2/phase2/Data/Requests/Requests.txt");
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        try(FileWriter writer = new FileWriter("phase2/phase2/Data/Requests/Requests.txt", true)){
            writeAllAccountRequests(writer);
            writeAllUndoRequests(writer);
            writeAllUserRequests(writer);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void writeAllUndoRequests(FileWriter writer) throws IOException{
        for(UndoRequest req: ATM.undoRequests){
            writer.write(req.toString() + "\n");
        }
    }


    private void writeAllUserRequests(FileWriter writer)throws IOException{
        for(UserRequest req: ATM.b.userRequests){
            writer.write(req.toString() + "\n");
        }
    }

    private void writeAllAccountRequests(FileWriter writer)throws IOException{
        for(AccountRequest req: ATM.b.accountRequests){
            writer.write(req.toString() + "\n");
        }
    }





}