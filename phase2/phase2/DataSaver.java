package phase2;

import java.io.*;
import java.util.ArrayList;

public class DataSaver {

    /**
     * The file that stores the ATM data.
     */
    private static String data = "phase2/phase2/data.txt";

    /**
     * Initializes an instance of DataWriter.
     */
    private DataSaver() {}

    /**
     * Writes the data from the ATM into data.txt.
     */
    public static void writeData() {
        try (FileWriter writer = new FileWriter(data)) {
            writer.write("ATM CLOCK");
            writer.write(ATM.clock.toString());

            writer.write("ATM CASH MACHINE");
            writer.write(ATM.c.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the data from the data.txt and enters it into the ATM.
     */
    public static void readData() {
        String s;
        try (BufferedReader reader = new BufferedReader(new FileReader(data))) {
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

}