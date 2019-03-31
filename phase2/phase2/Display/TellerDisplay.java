package phase2.Display;

import phase2.Accounts.Account;
import phase2.Accounts.AccountManager;
import phase2.People.*;

import java.util.ArrayList;
import java.util.Scanner;

public class TellerDisplay {

    /**
     * The User.
     */
    public static final BankTeller b = (BankTeller) UserManager.getUser(ATM.username);
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
        ATMController controller = new ATMController();
        System.out.println("Type '0' if you would like to change your password" + "\n"
                + "Type '1' if you would like to interact with your accounts" + "\n"
                + "Type '2' if you would like to log out" + "\n" +
                " Type '3' to merge accounts with another user");
        Scanner tmp = new Scanner(System.in);
        String command = tmp.nextLine();
        command = command.replaceAll("//s","");
        switch (command) {
            case "0":
                System.out.println("Enter new password:");
                command = tmp.nextLine();
                command = command.replaceAll("//s", "");
                b.setPassword(command);
                UserDisplay.main(null);
                break;
            case "1":
                System.out.println(b.balanceSummary());
                ArrayList<Account> account = new ArrayList<>();
                System.out.println("Please select the type of account you want to access");
                System.out.println("Type 'lc' for line of credit account" + "\n" + "Type 'cc' for credit card" + "\n"
                        + "Type 'c' for chequing" + "\n" + "Type 's' for savings");
                command = tmp.nextLine();
                switch (command) {
                    case ("lc"):
                        account = b.getAccountList("lc");
                        break;
                    case ("cc"):
                        account = b.getAccountList("cc");
                        break;
                    case ("sv"):
                        account = b.getAccountList("sv");
                        break;
                    case ("cq"):
                        account = b.getAccountList("cq");
                        break;
                    case ("cb"):
                        account = b.getAccountList("cb");
                        break;
                }
                accountType = command;
                a = null;
                if (account.size() == 0) {
                    System.out.println("There are no accounts to choose from here! Do you want to request an account?");
                    command = tmp.nextLine();
                    command = command.replaceAll("//s", "");
                    controller.incorrectAns(tmp,command);
                    if (command.equals("yes")) {
                        System.out.println("Enter the three digit currency code of the currency you want");
                        command = tmp.nextLine();
                        command = command.replaceAll("//s", "");
                        b.requestAccount(accountType,command);
                        System.out.println("Your request has been made!");
                    }else{UserDisplay.main(null);}
                    UserDisplay.main(null);
                } else if (account.size() == 1) {
                    a = account.get(0);
                    AccountDisplay.main(null);
                } else {
                    System.out.println("Enter the number of the account you wish to access: ");
                    command = tmp.nextLine();
                    int number = Integer.valueOf(command.replaceAll("//s", ""));
                    while (number > account.size()) {
                        System.out.println("There is no account relative to this index. " +
                                "Please input an appropriate index");
                        command = tmp.nextLine();
                        number = Integer.valueOf(command.replaceAll("//s", ""));
                    }
                    a = account.get(number);
                    AccountDisplay.main(null);
                }
                break;
            case "2":
                ATM.main(null);
                break;
            case "3":
                System.out.println("Type the username of the User you wish to join accounts with");
                command = tmp.nextLine();
                User merger = UserManager.getUser(command);
                while(merger == null) {
                    System.out.println("There is no user with such a username. Please try again");
                    command = tmp.nextLine();
                    merger = UserManager.getUser(command);
                }
                System.out.println("Input the account number for the account you wish to share");
                int number = Integer.valueOf(tmp.nextLine().replaceAll("//s",""));
                AccountManager AM = new AccountManager(b);
                Account a = AM.getAccount(number);
                while (a==null) {
                    System.out.print("There is no account relative to this index, Please select a valid account number");
                    number = Integer.valueOf(tmp.nextLine().replaceAll("//s",""));
                    a = AM.getAccount(number);
                }
                b.addAccountOwner(command, number);
        }
    }
}
