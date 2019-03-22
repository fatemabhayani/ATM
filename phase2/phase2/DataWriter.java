package phase2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class DataWriter {
    /**
     * The file that stores the ATM data.
     */
    private static String data = "phase2/phase2/data.txt";

    /**
     * Initializes an instance of DataWriter.
     */
    private DataWriter() {}

    /**
     * Writes the data from the ATM into data.txt.
     */
    public static void writeData() {
        try (FileWriter writer = new FileWriter(data)) {

        } catch (Exception e) {
            System.out.println("Error writing data.");
        }
    }

    /**
     * Read the data from the data.txt and enters it into the ATM.
     */
    public static void readData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(data))){
            String s = reader.readLine();
            while (s != null) {

            }
        } catch (Exception e) {
            System.out.println("Error reading data.");
        }
    }

}
