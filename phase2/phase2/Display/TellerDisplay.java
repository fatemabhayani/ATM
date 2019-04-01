package phase2.Display;

import phase2.Accounts.Account;
import phase2.People.*;
import phase2.Request.Request;

import java.util.ArrayList;
import java.util.Scanner;

public class TellerDisplay {

    /**
     * The entry point of User Display, access through ATM.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ATMController controller = new ATMController();
        BankTeller u = (BankTeller) UserManager.getUser(args[0]);
        String[] args1 = new String[3];
        args1[0] = args[0];

        Scanner sc = new Scanner(System.in);
        String command;

        System.out.println("Type '0' if you would like to change your password." + "\n"
                + "Type '1' if you would like to interact with your accounts." + "\n"
                + "Type '2' if you would like to log out." + "\n" +
                " Type '3' to merge accounts with another user." + "\n" +
                " Type '4' to restock the cash machine." + "\n" +
                " Type '5' to view your requests.");
        Scanner tmp = new Scanner(System.in);
        command = tmp.nextLine().replaceAll("//s","");
        while (!(command.equals("0") || command.equals("1") || command.equals("2") || command.equals("3") ||
                command.equals("4") || command.equals("5"))) {
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
                    controller.incorrectAns(tmp, command);
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
            case("4"):
                u.restockCashMachine();
                TellerDisplay.main(args);
                break;
            case("5"):
                System.out.println(u.getRequestSummary());
                if (u.getRequestSummary().equals("You have no requests.")) {
                    ManagerDisplay.main(null);
                }
                System.out.println("Which request do you want to address? Put in the appropriate number only." + "\n" +
                        "Type '0' for the oldest request.");
                command = sc.nextLine().replaceAll("//s", "");
                int i = Integer.valueOf(command);
                while (i > u.getNumberOfRequests()){
                    System.out.println("This index is not assigned to a request. Please try again.");
                    command = sc.nextLine().replaceAll("//s", "");
                    i = Integer.valueOf(command);
                }
                Request request = u.getRequest(i);
                System.out.println(request.toString());
                System.out.println("Do you want to accept this request?");
                command = sc.nextLine().replaceAll("//s", "");
                controller.incorrectAns(sc,command);
                if (command.toLowerCase().equals("yes")) {
                    u.completeRequest(i);
                } else {
                    u.ignoreRequest(i);
                }
                TellerDisplay.main(args);
        }
    }
}
