package phase2.Display;

import phase2.Accounts.Account;
import phase2.Accounts.AccountManager;
import phase2.People.User;
import phase2.People.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type User display.
 */
class UserDisplay {

    /**
     * The entry point of User Display, access through ATM.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        User u = UserManager.getUser(args[0]);
        ATMController con = new ATMController();
        String[] args1 = new String[3];
        args1[0] = args[0];

        System.out.println("Type '0' if you would like to change your password." + "\n"
                + "Type '1' if you would like to interact with your accounts." + "\n"
                + "Type '2' if you would like to log out." + "\n" +
                " Type '3' to merge accounts with another user.");
        Scanner tmp = new Scanner(System.in);
        String command = tmp.nextLine().replaceAll("//s","");
        while (!(command.equals("0") || command.equals("1") || command.equals("2") || command.equals("3"))) {
            System.out.println("You have not selected a valid action. Try again.");
            command = tmp.nextLine().replaceAll("//s","");
        }
        switch (command) {
            case "0":
                System.out.println("Enter your new password:");
                command = tmp.nextLine().replaceAll("//s", "");
                u.setPassword(command);
                System.out.println("Your password has been changed.");
                UserDisplay.main(args);
                break;
            case "1":
                System.out.println("This is the summary of your account balances.");
                System.out.println(u.balanceSummary());
                System.out.println("Please select the type of account you want to access.");
                System.out.println("Type 'lc' for line of credit account." + "\n" + "Type 'cc' for credit card account." + "\n"
                        + "Type 'cq' for chequing account." + "\n" + "Type 'sv' for savings account." + "\n" +
                        "Type 'cb' for cash back card account.");
                command = tmp.nextLine().toLowerCase();
                while (!(command.equals("lc") || command.equals("cc") || command.equals("cb") || command.equals("cq") ||
                        command.equals("sv"))) {
                    System.out.println("You have not selected a valid account type. Try again.");
                    command = tmp.nextLine().toLowerCase();
                }
                String accountType = command;
                args1[1] = accountType;
                ArrayList<Account> account = u.getAccountList(accountType);

                if (account.size() == 0) {
                    System.out.println("There are no accounts to choose from here! Do you want to request an account?");
                    command = tmp.nextLine().replaceAll("//s", "").toLowerCase();
                    con.incorrectAns(tmp, command);
                    if (command.equals("yes")) {
                        System.out.println("Enter the three digit currency code of the currency you want. Please " +
                                "enter a valid currency code.");
                        command = tmp.nextLine().replaceAll("//s", "");
                        u.requestAccount(accountType, command);
                        System.out.println("Your request has been made!");
                    } else { UserDisplay.main(args); }
                    UserDisplay.main(args);

                } else {
                    System.out.println(u.accountSummary(accountType));
                    System.out.println("Enter the number of the account you wish to access: ");
                    int number = Integer.valueOf(tmp.nextLine().replaceAll("//s",""));
                    while (u.hasAccount(number)) {
                        System.out.println("You do not have an account with this number. Try again.");
                        number = Integer.valueOf(tmp.nextLine().replaceAll("//s",""));
                    }
                    args1[2] = String.valueOf(number);
                    AccountDisplay.main(args1);
                }
                break;
            case "2":
                ATM.main(null);
                break;
            case "3":
                System.out.println("Type the username of the user you wish to join accounts with.");
                command = tmp.nextLine();
                User merger = UserManager.getUser(command);
                while (merger == null) {
                    System.out.println("There is no user with such a username. Please try again.");
                    command = tmp.nextLine();
                    merger = UserManager.getUser(command);
                }
                System.out.println("Input the account number for the account you wish to share.");
                int number = Integer.valueOf(tmp.nextLine().replaceAll("//s",""));
                while (u.hasAccount(number)) {
                    System.out.println("You do not have an account with this number. Try again.");
                    number = Integer.valueOf(tmp.nextLine().replaceAll("//s",""));
                }
                u.addAccountOwner(command, number);
                break;
        }
    }

}