package phase2.Display;

import phase2.Accounts.Account;
import ForeignCurrency;
import phase2.Transactions.Bill;
import phase2.People.User;

import java.util.Scanner;

/**
 * The type Bill display.
 */
class BillDisplay {

    private static User U = AccountDisplay.U;
    private static Account a = AccountDisplay.a;

    /**
     * The entry point of Bill Display, accessed from ATM
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner tmp = new Scanner(System.in);
        System.out.println("Enter the sum of money you wish to pay:");
        String command = tmp.nextLine();
        double amt = Double.valueOf(command.replaceAll("//s", ""));
        System.out.println("Enter the three digit currency code of the currency you wish to pay the bill in");
        command = tmp.nextLine();
        command = command.replaceAll("//s", "");
        Bill t = new Bill(new ForeignCurrency(command, amt), a, ATM.clock.getCurrentTime());
        U.makeTransaction(t);

        AccountDisplay.main(null);
    }

}