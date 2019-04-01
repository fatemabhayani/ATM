package phase2.Display;

import phase2.*;
import phase2.Data.*;
import phase2.People.*;
import phase2.Request.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * An ATM simulation.
 */
public class ATM {

    /**
     * The collection of user accounts.
     */
    public static ArrayList<User> bankUsers = new ArrayList<>();

    /**
     * The collection of requests.
     */
    public static ArrayList<UndoRequest> undoRequests = new ArrayList<>();

    /**
     * The username.
     */
    public static String username;

    /**
     * Creates the ATM interface,
     * User or BankManager can login, User can request
     * to create an account. Date and Time are also set.
     * If the user does not have an account, a new User will be created
     *
     * @param args the args
     */
    public static void main(String[] args){
        ATMController con = new ATMController();

        if (ATMTime.getInstance().isFirstOfMonth()) { UserManager.updateInterest(); }

        String command;

        System.out.println("Welcome to the ATM, I am an incredibly well known superhero, my name is " +
                generateRandomSuperhero() + " and I will be helping you today.");
        System.out.println("Type 'exit program' to log out. Otherwise type 'yes' or 'no'.");
        System.out.println("Do you have an account?");

        Scanner sc = new Scanner(System.in);
        String isUser = sc.nextLine().toLowerCase();
        if (isUser.equals("exit program")) {
            con.logOut();
        }

        while (!(isUser.equals("no") || isUser.equals("yes"))) {
            System.out.println("You did not give a valid answer, try again.");
            isUser = sc.nextLine().toLowerCase();
        }
        if (isUser.equals("yes")) {
            System.out.println("Glad you're back! Please input your username.");
            command = sc.nextLine().replaceAll("//s", "");
            if (UserManager.isBankManager(command)) {
                con.verifyBankManager(sc);
            } else {
                while (!UserManager.authenticateUsername(command)) {
                    System.out.println("You did not give a valid username, try again! If you would like to go back to " +
                            "the login screen, type 'exit'.");
                    command = sc.nextLine();

                    if (UserManager.isBankManager(command)) {
                        con.verifyBankManager(sc);
                    } else if (command.toLowerCase().equals("exit")) {
                        ATM.main(null);
                    }
                }
                User u = UserManager.getUser(command);

                System.out.println("Please enter your password.");
                command = sc.nextLine().replaceAll("//s", "");

                while(!UserManager.authenticatePassword(command, u)) {
                    System.out.println("You did not give a valid password, try again! If you would like to go back to " +
                            "the start screen, type 'exit'.");
                    command = sc.nextLine().replaceAll("//s", "");
                    if (command.toLowerCase().equals("exit")) {
                        ATM.main(null);
                    }
                }
                args = new String[1];
                args[0] = u.getUsername();
                if (u.getUsername().length() > 6 && u.getUsername().substring(0, 6).equals("teller")) {
                    TellerDisplay.main(args);
                } else {
                    UserDisplay.main(args);
                }
            }
        } else {
            System.out.println("Welcome to the ATM! Please choose a username.");
            command = sc.nextLine().replaceAll("//s", "");
            while (UserManager.authenticateUsername(command)) {
                System.out.println("The username you have chosen is already taken. Try another username or type 'exit'" +
                        " to leave.");
                command = sc.nextLine().replaceAll("//s", "");
            }
            System.out.println("You have chosen a valid username.");
            String username = command;
            System.out.println("Now choose your initial password.");
            command = sc.nextLine().replaceAll("//s", "");
            BankManager.getInstance().addRequest(new UserRequest(username, command));
            System.out.println("Your request to create an account has been sent to the bank manager. You will be " +
                    "sent back to login page now.");
            ATM.main(null);
        }

    }

    private static String generateRandomSuperhero() {
        Random random = new Random();
        String name = "Random Superhero";
        int number = random.nextInt(46);
        try(BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/Data/superhero.txt"))){
            for(int i = 0; i < number; i++){
                name = reader.readLine();
            }
        } catch (Exception e){
            name = "Error, the superhero left!".toUpperCase();
        }
        return name;
    }
}