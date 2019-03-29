package phase2.Data;

import java.io.*;
import java.util.GregorianCalendar;

import phase2.Display.ATM;
import phase2.ForeignCurrency;
import phase2.People.*;
import phase2.Accounts.*;
import phase2.Transactions.*;

/**
 * The type Data saver.
 */
public class DataSaver {

    public static void main(String[] args) {

        User user = new User("yes", "yes");
        ATM.bankEmployees.add(new BankTeller("yes", "yes"));
        Savings s = new Savings(new GregorianCalendar(), user, "CAD", 0);
        user.getAccountManager().add(s);
        Deposit b = new Deposit(new ForeignCurrency("CAD", 50), s, new GregorianCalendar());
        user.makeTransaction(b);

        DataSaver d = new DataSaver();
        d.writeUserData(user);


    }

    /**
     * The files that stores the ATM data.
     */
    private String atmdata = "phase2/phase2/atmdata.txt";
    private String userdata = "phase2/phase2/Data/userdata.txt";
    private String employeedata = "phase2/phase2/Data/employeedata.txt";
    private String transactiondata = "phase2/phase2/Data/transactiondata.txt";

    /**
     * Initializes an instance of DataSaver.
     */
    private DataSaver() {}


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
        for(User user: ATM.bankUsers){
            writeUserData(user);
        }
    }


    private void writeUserData(User user){
        appendName(user);
        try (FileWriter writer = new FileWriter("phase2/phase2/Data/UserDataFiles/" + user.getUsername() + ".txt")){
            writeName(writer, user);
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
    private void writeName(FileWriter writer, User user) throws IOException{
        writer.write(user.toString() +"\n");
    }

    private void writeSavingAccount(FileWriter writer, Savings s) throws IOException {
        writer.write(s.toString() +" ");
        writer.write(s.transactionString() + "\n");
    }

    private void writeAllSavings(FileWriter writer, User user) throws IOException{
        writer.write("SAVINGS \n");
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
        writer.write("CHEQUING \n");
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
        writer.write("CREDIT CARD \n");
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
        writer.write("LINE OF CREDIT \n");
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
        writer.write("CASH BACK CARD \n");
        for(Account account: user.getAccountList("cb")){
            CashBackCard c = (CashBackCard) account;
            writeCashBackCardAccount(writer, c);
        }
    }



}