package phase2.Data;

import java.io.*;
import phase2.Display.ATM;
import phase2.People.*;
import phase2.Accounts.*;

/**
 * The type Data saver.
 */
public class DataSaver {

    /**
     * The files that stores the ATM data.
     */
    private static String atmdata = "phase2/phase2/atmdata.txt";
    private static String userdata = "phase2/phase2/userdata.txt";
    private static String employeedata = "phase2/phase2/employeedata.txt";
    private static String transactiondata = "phase2/phase2/transactiondata.txt";

    /**
     * Initializes an instance of DataSaver.
     */
    private DataSaver() {}

    /**
     * Writes the data from the ATM.
     */
    public static void writeData() {
        try {
            writeATMData();
            writeUserData();
            writeTransactionData();
            writeEmployeeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the time and cash machine contents into atmdata.txt.
     */
    private static void writeATMData() {
        try (FileWriter writer = new FileWriter(atmdata)) {
            writer.write("ATM CLOCK");
            writer.write(ATM.clock.toString());
            writer.write("ATM CASH MACHINE");
            writer.write(ATM.c.toString());
            writer.write("ATM ACCOUNT NUMBER");
            writer.write(UserManager.accountNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the bankUsers contents into userdata.txt.
     */
    private static void writeUserData() {
        try (FileWriter writer = new FileWriter(userdata)) {
             writer.write("ATM BANK USERS");
             for (User u: ATM.bankUsers) {
                 writer.write("USER");
                 writer.write(u.toString());
                 writer.write("SAVINGS");
                 for (Object o : u.getAccountList("sv")) {
                     Savings a = (Savings) o;
                     writer.write("ACCOUNT");
                     writer.write(a.toString());
                 }
                 writer.write("CHEQUING");
                 for (Object o : u.getAccountList("cq")) {
                     Chequing a = (Chequing) o;
                     writer.write("ACCOUNT");
                     writer.write(a.toString());
                 }
                 writer.write("CASH BACK");
                 for (Object o : u.getAccountList("cb")) {
                     CashBackCard a = (CashBackCard) o;
                     writer.write("ACCOUNT");
                     writer.write(a.toString());
                 }
                 writer.write("CREDIT CARD");
                 for (Object o : u.getAccountList("cc")) {
                     CreditCard a = (CreditCard) o;
                     writer.write("ACCOUNT");
                     writer.write(a.toString());
                 }
                 writer.write("LINE OF CREDIT");
                 for (Object o : u.getAccountList("lc")) {
                     LineOfCredit a = (LineOfCredit) o;
                     writer.write("ACCOUNT");
                     writer.write(a.toString());
                 }
                 writer.write("");
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the transaction information into transactiondata.txt.
     */
    private static void writeTransactionData() {
        try (FileWriter writer = new FileWriter(transactiondata)) {
            writer.write("ATM BANK USERS");
            for (User u: ATM.bankUsers) {
                writer.write("USER");
                writer.write(u.toString());
                writer.write("SAVINGS");
                for (Object o : u.getAccountList("sv")) {
                    Savings a = (Savings) o;
                    writer.write("ACCOUNT");
                    writer.write(a.accountNum);
                    writer.write(a.transactionString());
                }
                writer.write("CHEQUING");
                for (Object o : u.getAccountList("cq")) {
                    Chequing a = (Chequing) o;
                    writer.write("ACCOUNT");
                    writer.write(a.accountNum);
                    writer.write(a.transactionString());
                }
                writer.write("CASH BACK");
                for (Object o : u.getAccountList("cb")) {
                    CashBackCard a = (CashBackCard) o;
                    writer.write("ACCOUNT");
                    writer.write(a.accountNum);
                    writer.write(a.transactionString());
                }
                writer.write("CREDIT CARD");
                for (Object o : u.getAccountList("cc")) {
                    CreditCard a = (CreditCard) o;
                    writer.write("ACCOUNT");
                    writer.write(a.accountNum);
                    writer.write(a.transactionString());
                }
                writer.write("LINE OF CREDIT");
                for (Object o : u.getAccountList("lc")) {
                    LineOfCredit a = (LineOfCredit) o;
                    writer.write("ACCOUNT");
                    writer.write(a.accountNum);
                    writer.write(a.transactionString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the employee information into employeedata.txt.
     */
    private static void writeEmployeeData() {
        try (FileWriter writer = new FileWriter(employeedata)) {
            writer.write("ATM BANK MANAGER");
            writer.write(ATM.b.toString());
            writer.write("ATM BANK TELLERS");
            for (BankTeller b : ATM.bankEmployees) {
                writer.write(b.getUsername().toUpperCase());
                writer.write(b.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}