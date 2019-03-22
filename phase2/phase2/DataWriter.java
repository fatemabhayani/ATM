package phase2;

import java.io.*;

public class DataWriter {
    /**
     * The file that stores the ATM data.
     */
    private static String data = "phase2/phase2/data.ser";

    /**
     * Initializes an instance of DataWriter.
     */
    private DataWriter() {}

    /**
     * Writes the data from the ATM into data.ser.
     */
    public static void writeData() {
        try {
            FileOutputStream fileOut = new FileOutputStream(data);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(ATM.b);

            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in data.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the data from the data.ser and enters it into the ATM.
     */
    public static void readData() {
        try {
            FileInputStream fileIn = new FileInputStream(data);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object e = in.readObject();

            in.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
