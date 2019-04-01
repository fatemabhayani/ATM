package phase2.Display;

import phase2.ATMTime;
import phase2.Accounts.Account;
import phase2.People.User;
import phase2.People.UserManager;
import phase2.Tradable.ForeignCurrency;
import phase2.Transactions.Transfer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * The type Transfer display.
 */
class TransferDisplay {

    /**
     * The entry point of Transfer display, access from ATM
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        User u = UserManager.getUser(args[0]);
        Account a = UserManager.getUserAccount(Integer.valueOf(args[2]));

        System.out.println("Please enter the account number of the account you would like to transfer to.");
        Scanner tmp = new Scanner(System.in);
        int number = Integer.valueOf(tmp.nextLine().replaceAll("//s", ""));
        while (number >= UserManager.accountNum) {
            System.out.println("There is no such account with this number. Try again.");
        }
        Account a2 = UserManager.getUserAccount(number);
        System.out.println("Enter the sum of money you wish to transfer");
        String command = tmp.nextLine();
        double amt = Double.valueOf(command.replaceAll("//s", ""));
        System.out.println("Enter the three digit currency code of the currency you wish to transfer. Please enter a " +
                "valid currency code.");
        command = tmp.nextLine().replaceAll("//s", "");
        Transfer t = new Transfer(new ForeignCurrency(command, amt), a, a2, ATMTime.getInstance().getCurrentTime());
        u.makeTransaction(t);

        AccountDisplay.main(args);
    }

}
