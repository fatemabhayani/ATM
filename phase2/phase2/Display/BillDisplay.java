package phase2.Display;

import phase2.ATMTime;
import phase2.Accounts.Account;
import phase2.People.UserManager;
import phase2.Tradable.ForeignCurrency;
import phase2.Transactions.Bill;
import phase2.People.User;

import java.util.Scanner;

/**
 * The type Bill display.
 */
class BillDisplay {

    /**
     * The entry point of Bill Display, accessed from ATM
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        User u = UserManager.getUser(args[0]);
        Account a = UserManager.getUserAccount(Integer.valueOf(args[2]));

        Scanner tmp = new Scanner(System.in);
        System.out.println("Enter the sum of money you wish to pay:");
        String command = tmp.nextLine();
        double amt = Double.valueOf(command.replaceAll("//s", ""));
        System.out.println("Enter the three digit currency code of the currency you wish to pay the bill in. Please " +
                "enter a valid currency code.");
        command = tmp.nextLine().replaceAll("//s", "");
        Bill t = new Bill(new ForeignCurrency(command, amt), a, ATMTime.getInstance().getCurrentTime());
        u.makeTransaction(t);

        AccountDisplay.main(args);
    }

}