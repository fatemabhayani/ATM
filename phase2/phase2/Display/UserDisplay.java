package phase2.Display;

import phase2.Accounts.Account;
import phase2.People.User;
import phase2.People.UserManager;
import phase2.Request.AccountRequest;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type User display.
 */
public class UserDisplay {


    /**
     * The User.
     */
    public static User U = UserManager.getUser(ATM.username);
    /**
     * The Account.
     */
    public static Account a;
    /**
     * The accountType.
     */
    static String accountType;

    /**
     * The entry point of User Display, access through ATM.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println("Type '0' if you would like to change your password" + "\n"
                + "Type '1' if you would like to interact with your accounts" + "\n"
                + "Type '2' if you would like to log out");
        Scanner tmp = new Scanner(System.in);
        String command = tmp.nextLine();
        command = command.replaceAll("//s","");
        switch (command) {
            case "0":
                System.out.println("Enter new password:");
                command = tmp.nextLine();
                command = command.replaceAll("//s", "");
                U.setPassword(command);
                UserDisplay.main(null);
                break;
            case "1":
                System.out.println(U.balanceSummary());
                ArrayList<Account> account = new ArrayList<>();
                System.out.println("Please select the type of account you want to access");
                System.out.println("Type 'lc' for line of credit account" + "\n" + "Type 'cc' for credit card" + "\n"
                        + "Type 'c' for chequing" + "\n" + "Type 's' for savings");
                command = tmp.nextLine();
                switch (command) {
                    case ("lc"):
                        account = U.getAccountList("lc");
                        break;
                    case ("cc"):
                        account = U.getAccountList("cc");
                        break;
                    case ("sv"):
                        account = U.getAccountList("sv");
                        break;
                    case ("cq"):
                        account = U.getAccountList("cq");
                        break;
                    case ("cb"):
                        account = U.getAccountList("cb");
                        break;
                }
                accountType = command;
                a = null;
                if (account.size() == 0) {
                    System.out.println("There are no accounts to choose from here! Do you want to request an account?");
                    command = tmp.nextLine();
                    command = command.replaceAll("//s", "");
                    if (command.equals("yes")) {
                        System.out.println("Enter the three digit currency code of the currency you want");
                        command = tmp.nextLine();
                        command = command.replaceAll("//s", "");
                        AccountRequest r = new AccountRequest(U, accountType, command);
                        ATM.b.addRequest(r);
                        System.out.println("Your request has been made!");
                    }
                    UserDisplay.main(null);
                } else if (account.size() == 1) {
                    a = account.get(0);
                    AccountDisplay.main(null);
                } else {
                    System.out.println("Enter the number of the account you wish to access: ");
                    command = tmp.nextLine();
                    int number = Integer.valueOf(command.replaceAll("//s", ""));
                    a = account.get(number);
                    AccountDisplay.main(null);
                }
                break;
            case "2":
                ATM.main(null);
                break;
        }
    }


}

