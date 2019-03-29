package phase2.Data;

import java.io.*;
import phase2.Display.ATM;
import phase2.People.*;
import phase2.Accounts.*;

/**
 * The type Data saver.
 */
public class DataSaver {

    public static void main(String[] args) {

        ATM.bankUsers.add(new User("x", "x"));
        ATM.bankEmployees.add(new BankTeller("yes", "yes"));

        DataSaver d = new DataSaver();
        d.writeData();

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
     * Writes the data from the ATM.
     */
    public void writeData() {
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

    /**
     * Writes the bankUsers contents into userdata.txt.
     */
    private void writeUserData() {
        try (FileWriter writer = new FileWriter(userdata)) {
             writer.write("ATM BANK USERS");
             for (User u: ATM.bankUsers) {
                 writer.write("USER");
                 writer.write(u.toString());
                 writer.write("SAVINGS");
                 for (Account account : u.getAccountList("sv")) {
                     Savings a = (Savings) account;
                     writer.write(a.toString());
                 }
                 writer.write("CHEQUING");
                 for (Object o : u.getAccountList("cq")) {
                     Chequing a = (Chequing) o;
                     writer.write(a.toString());
                 }
                 writer.write("CASH BACK");
                 for (Object o : u.getAccountList("cb")) {
                     CashBackCard a = (CashBackCard) o;
                     writer.write(a.toString());
                 }
                 writer.write("CREDIT CARD");
                 for (Object o : u.getAccountList("cc")) {
                     CreditCard a = (CreditCard) o;
                     writer.write(a.toString());
                 }
                 writer.write("LINE OF CREDIT");
                 for (Object o : u.getAccountList("lc")) {
                     LineOfCredit a = (LineOfCredit) o;
                     writer.write(a.toString());
                 }
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the transaction information into transactiondata.txt.
     */
    private void writeTransactionData() {
        try (FileWriter writer = new FileWriter(transactiondata)) {
            writer.write("ATM TRANSACTIONS");
            for (User u: ATM.bankUsers) {
                // We only write a user to the file if the user has made any transactions.
                if (u.getAccountManager().getTransactions().size() > 0) {
                    writer.write("USER");
                    writer.write(u.toString());
                    for (Object o : u.getAccountList("sv")) {
                        Savings a = (Savings) o;
                        // We only write an account to the file if there are any transactions made on it.
                        if (a.getTransactions().size() > 0) {
                            writer.write("ACCOUNT: " + a.accountNum);
                            writer.write(a.transactionString());
                        }
                    }
                    for (Object o : u.getAccountList("cq")) {
                        Chequing a = (Chequing) o;
                        if (a.getTransactions().size() > 0) {
                            writer.write("ACCOUNT: " + a.accountNum);
                            writer.write(a.transactionString());
                        }
                    }
                    for (Object o : u.getAccountList("cb")) {
                        CashBackCard a = (CashBackCard) o;
                        if (a.getTransactions().size() > 0) {
                            writer.write("ACCOUNT: " + a.accountNum);
                            writer.write(a.transactionString());
                        }
                    }
                    for (Object o : u.getAccountList("cc")) {
                        CreditCard a = (CreditCard) o;
                        if (a.getTransactions().size() > 0) {
                            writer.write("ACCOUNT: " + a.accountNum);
                            writer.write(a.transactionString());
                        }
                    }
                    for (Object o : u.getAccountList("lc")) {
                        LineOfCredit a = (LineOfCredit) o;
                        if (a.getTransactions().size() > 0) {
                            writer.write("ACCOUNT: " + a.accountNum);
                            writer.write(a.transactionString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the employee information into employeedata.txt.
     */
    private void writeEmployeeData() {
        try (FileWriter writer = new FileWriter(employeedata)) {
            writer.write("ATM BANK MANAGER \n");
            writer.write(ATM.b.toString() + "\n");
            writer.write("ATM BANK TELLERS \n");
            for (BankTeller b : ATM.bankEmployees) {
                writer.write(b.getUsername() + "." + b.getPassword() + "\n");
                writer.write(b.toString() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}