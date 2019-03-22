package phase2;

import java.util.ArrayList;
import java.util.Scanner;

public class UserDisplay {

    public static String command;
    public static User U = UserManager.getUser(ATM.username);
    public static Account a;
    public static String accountType;

    public static void main(String[] args) {
        System.out.println("Type '0' if you would like to change your password" + "\n"
                + "Type '1' if you would like to interact with your accounts" + "\n"
                + "Type '2' if you would like to log out");
        Scanner tmp = new Scanner(System.in);
        command = tmp.nextLine();
        command = command.replaceAll("//s","");
        switch (command) {
            case "0":
                System.out.println("Enter new password:");
                command = tmp.nextLine();
                command = command.replaceAll("//s", "");
                U.setPassword(command);
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
                        account = U.getAccount("lc");
                        break;
                    case ("cc"):
                        account = U.getAccount("cc");
                        break;
                    case ("s"):
                        account = U.getAccount("s");
                        break;
                    case ("c"):
                        account = U.getAccount("c");
                        break;
                }
                accountType = command;
                a = null;
                if (account.size() == 0) {
                    System.out.println("There are no accounts to choose from here! Do you want to request an account?");
                    command = tmp.nextLine();
                    command = command.replaceAll("//s", "");
                    if (command.equals("yes")) {
                        AccountRequest r = new AccountRequest(U, accountType);
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

