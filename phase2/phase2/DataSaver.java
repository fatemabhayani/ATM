package phase2;

import java.io.*;
import phase2.Display.ATM;
import phase2.People.*;

public class DataSaver {

    /**
     * The files that stores the ATM data.
     */
    private static String atmdata = "phase2/phase2/atmdata.txt";
    private static String userdata = "phase2/phase2/userdata.txt";
    private static String employeedata = "phase2/phase2/employeedata.txt";

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
            writer.write("\n");
            writer.write("ATM CASH MACHINE");
            writer.write(ATM.c.toString());
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

                 writer.write("CHEQUING");

                 writer.write("CASH BACK");

                 writer.write("CREDIT CARD");

                 writer.write("LINE OF CREDIT");
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the data and enters it into the ATM.
     */
    public static void readData() {
        try {
            readATMData();
            readUserData();
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