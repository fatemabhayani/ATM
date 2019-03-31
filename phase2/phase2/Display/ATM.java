package phase2.Display;

import phase2.*;
import phase2.Accounts.*;
import phase2.Data.DataReader;
import phase2.Data.DataSaver;
import phase2.People.*;
import phase2.Request.*;
import phase2.Tradable.ForeignCurrency;
import phase2.Transactions.Deposit;

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
     * The time recorded by the ATM.
     */
    public static ATMTime clock = new ATMTime(2019, 0, 1, 0, 0, 0);

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
        DataReader d = new DataReader();
        d.readATMData();
        d.readAllUserData();
        d.readAllRequests();

        String command;

        System.out.println("Welcome to the ATM, I am an incredibly well known superhero, my name is " + generateRandomSuperhero() +
                " and I will be helping you today.");
        System.out.println("Do you have an account?");

        Scanner sc = new Scanner(System.in);
        String isUser = sc.nextLine();
        while (!(isUser.toLowerCase().equals("no") ||isUser.toLowerCase().equals("yes"))){
            System.out.println("You did not give a valid answer, try again");
            isUser = sc.nextLine();
        }
        if (isUser.toLowerCase().equals("yes")){
            System.out.println("Please input your username");
            command = sc.nextLine();
            command = command.replaceAll("//s", "");
            if (command.equals("bankmanager")){
                System.out.println("Please input your password");
                command = sc.nextLine();
                command = command.replaceAll("//s", "");

                while (!(command.toLowerCase().equals("bestboss"))){
                    System.out.println("You did not give a valid password, try again, if you would like to go to the start screen type exit");
                    command = sc.nextLine();
                    if(command.toLowerCase().equals("exit")){
                        ATM.main(null);
                    }
                }
                ManagerDisplay.main(null);

            } else {
                User user = UserManager.getUser(command);
                while (user == null) {
                    System.out.println("You did not give a valid username, try again, if you would like to go to the start screen type exit");
                    command = sc.nextLine();

                    if (command.equals("bankmanager")) {
                        System.out.println("Please input your password");
                        command = sc.nextLine();
                        command = command.replaceAll("//s", "");

                        while (!(command.toLowerCase().equals("bestboss"))) {
                            System.out.println("You did not give a valid password, try again, if you would like to go to the start screen type exit");
                            command = sc.nextLine();
                            if (command.toLowerCase().equals("exit")) {
                                ATM.main(null);
                            }
                        }
                        ManagerDisplay.main(null);
                    }

                    user = UserManager.getUser(command);
                    if(command.toLowerCase().equals("exit")){
                        ATM.main(null);
                    }
                }
                username = command;
                System.out.println("Enter your password.");
                command = sc.nextLine();
                command = command.replaceAll("//s", "");
                while(! UserManager.authenticatePassword(command, user)) {
                    System.out.println("You did not give a valid password, try again, if you would like to go to the start screen type exit");
                    command = sc.nextLine();
                    command = command.replaceAll("//s", "");
                    if(command.toLowerCase().equals("exit")){
                        ATM.main(null);
                    }
                }
                UserDisplay.main(null);
            }

        } else if (isUser.toLowerCase().equals("no")){
            System.out.println("Please choose a username");
            command = sc.nextLine();
            command = command.replaceAll("//s", "");
            while(UserManager.authenticateUsername(command)){
                System.out.println("The username you have chosen is already taken, try another username or type exit to leave");
                command = sc.nextLine();
                command = command.replaceAll("//s", "");
            }
            System.out.println("You have chosen a valid username");
            String username = command;
            System.out.println("Now choose your initial password.");
            command = sc.nextLine().replaceAll("//s", "");
            System.out.println("Your request to create an account has been sent to the bank manager");
            UserRequest r = new UserRequest(username, command);
            BankManager.getInstance().addRequest(r);
            ATM.main(null);
        }

        if (clock.isFirstOfMonth()) {
            UserManager.updateInterest();
        }

        //TODO: write data only once per run (before program resets at midnight)


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

