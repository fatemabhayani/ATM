package phase2.Display;

import phase2.People.BankManager;
import phase2.Request.AccountRequest;
import phase2.Accounts.Account;
import phase2.People.User;

import java.util.Scanner;

/**
 * The type Account display.
 */
class AccountDisplay {


    /**
     * The User.
     */
    public static User U = UserDisplay.U;
    /**
     * The Account.
     */
    public static Account a = UserDisplay.a;
    private static String accountType = UserDisplay.accountType;

    /**
     * The entry point of Account Display.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println(a.toString());
        System.out.println("Do you want to request an account (1), make a transaction (2), or exit (3)?");
        Scanner tmp = new Scanner(System.in);
        String command = tmp.nextLine();
        command = command.replaceAll("//s","");
        if (command.equals("1")) {
            System.out.println("Enter the currency code for the account?");
            command = tmp.nextLine();
            command = command.replaceAll("//s","");
            AccountRequest r = new AccountRequest(U, accountType, command.toUpperCase());
            BankManager.getInstance().addRequest(r);
            System.out.println("Your request has been made!");
            AccountDisplay.main(null);
        } else if (command.equals("2")) {
            System.out.println("Do you want to make a transfer (1), deposit (2), withdrawal (3), or bill payment (4)?");
            command = tmp.nextLine();
            command = command.replaceAll("//s","");
            switch (command) {
                case "1":
                    TransferDisplay.main(null);
                    break;
                case "2":
                    DepositDisplay.main(null);
                    break;
                case "3":
                    WithdrawDisplay.main(null);
                    break;
                default:
                    BillDisplay.main(null);
                    break;
            }
        } else {
            UserDisplay.main(null);
        }
    }
}

