package phase2.Data;

import java.io.*;
import java.util.ArrayList;

import phase2.Display.ATM;
import ForeignCurrency;
import phase2.People.*;

/**
 * The type Data reader.
 */
public class DataReader {

    public static void main(String[] args) {
        ATM.bankUsers = new ArrayList<>();
        DataReader read = new DataReader();
        read.readAllUserData();
        System.out.println(ATM.bankUsers);
    }


    /**
     * Initializes an instance of DataSaver.
     */
    private DataReader() {
    }

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
//            String[] info = readNamePassword(reader);
//            ATM.bankUsers.add(new User(info[0], info[1]));
//            System.out.println(ATM.bankUsers);
            initializeUser(reader);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeUser(BufferedReader reader) throws IOException{
        String line = reader.readLine();
        String[] info = line.split("\\.", 2);
        ATM.bankUsers.add(new User(info[0], info[1]));
    }
}