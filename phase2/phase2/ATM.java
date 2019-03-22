package phase2;

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
    public static ArrayList<User> bankUsers;

    /**
     * The cash machine associated with the ATM.
     */
    public static CashMachine c;

    /**
     * The time recorded by the ATM.
     */
    public static ATMTime clock;

    public static String username;
    public static BankManager b = new BankManager();

    /**
     * Creates the ATM interface,
     * User or BankManager can login, User can request
     * to create an account. Date and Time are also set.
     * If the user does not have an account, a new User will be created
     */
    public static void main(String args[]){
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
                while (user == null){
                    System.out.println("You did not give a valid username, try again, if you would like to go to the start screen type exit");
                    command = sc.nextLine();
                    user = UserManager.getUser(command);
                }
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
            System.out.println("You have chosen a valid username, your request to create an account has been sent to the bank manager");
            UserRequest r = new UserRequest(command);
            ATM.b.addRequest(r);
            ATM.main(null);
        }

        if (clock.isFirstOfMonth()) {
            UserManager.updateSavings();
        }

    }

    private static String generateRandomSuperhero() {
        Random random = new Random();
        String name = "Random Superhero";
        int number = random.nextInt(46);
        try(BufferedReader reader = new BufferedReader(new FileReader("phase2/phase2/superhero.txt"))){
            for(int i = 0; i < number; i++){
                name = reader.readLine();

            }

        } catch (Exception e){
            name = "Error, the superhero left!".toUpperCase();
        }

        return name;

    }
}

