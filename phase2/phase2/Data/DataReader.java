package phase2.Data;

import java.io.*;
import phase2.Display.ATM;
import phase2.People.*;
import phase2.Accounts.*;

/**
 * The type Data reader.
 */
public class DataReader {

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
        try (BufferedReader reader = new BufferedReader(new FileReader(atmdata))) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read userdata.txt and enters it into the ATM.
     */
    private static void readUserData() {
        String s;
        try (BufferedReader reader = new BufferedReader(new FileReader(userdata))) {
            reader.readLine();
            s = reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read transactiondata.txt and enters it into the ATM.
     */
    private static void readTransactionData() {
        String s;
        try (BufferedReader reader = new BufferedReader(new FileReader(transactiondata))) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader(employeedata))) {
            reader.readLine();
            s = reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}