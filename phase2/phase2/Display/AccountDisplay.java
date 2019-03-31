package phase2.Display;

import phase2.People.BankManager;
import phase2.Request.AccountRequest;
import phase2.Accounts.Account;
import phase2.People.User;
import phase2.Transactions.Bill;

import java.util.Scanner;

/**
 * The type Account display.
 */
class AccountDisplay {


    /**
     * The User.
     */
    public static final User U = UserDisplay.U;
    /**
     * The Account.
     */
    public static final Account a = UserDisplay.a;
    private static final String accountType = UserDisplay.accountType;

    /**
     * The entry point of Account Display.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println(a.toString());
        System.out.println("Do you want to request an account (1), make a transaction (2), undo a transaction (3)," +
                " View most recent transaction (4) or exit (5)?");
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
        } else if (command.equals("3")) {
            if (a.getTransactions().size()>0) {
                System.out.println("Select the index of Transaction yu wish to undo");
                int number = Integer.valueOf(tmp.nextLine().replaceAll("//s",""));
                while (a.getTransactions().size() < number) {
                    System.out.println("The selected index does not relate to any transaction, Please try again");
                    number = Integer.valueOf(tmp.nextLine().replaceAll("//s",""));
                }
                if (a.getPastTransaction(number) instanceof Bill) {
                    System.out.println("You are not allowed to undo a bill payment");
                } else {
                    U.requestUndo(a, number);
                }
            } else {
                System.out.println("There are no transactions to be undone");
                AccountDisplay.main(null);
            }


        }else if (command.equals("4")){
            if (a.getTransactions().size()>0) {
                a.getPastTransaction(0);
                AccountDisplay.main(null);
            }else {
                System.out.println("There are no transactions to show");
                AccountDisplay.main(null);
            }


        }
        else {
            UserDisplay.main(null);
        }
    }
}

