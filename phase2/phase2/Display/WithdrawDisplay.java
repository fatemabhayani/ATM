package phase2.Display;

import phase2.Accounts.Account;
import ForeignCurrency;
import phase2.Transactions.Withdraw;
import phase2.People.User;

import java.util.Calendar;
import java.util.Scanner;

/**
 * The type Withdraw display.
 */
class WithdrawDisplay {

    private static User U = AccountDisplay.U;
    private static Account a = AccountDisplay.a;

    /**
     * The entry point of Withdraw Display, access through ATM
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner tmp = new Scanner(System.in);
        System.out.println("Enter the sum of money you wish to withdraw.");
        String command = tmp.nextLine();
        int amt = Integer.valueOf(command.replaceAll("//s", ""));
        Calendar time = ATM.clock.getCurrentTime();
        Withdraw t = new Withdraw(new ForeignCurrency("CAD", amt), a, time);
        U.makeTransaction(t);

        AccountDisplay.main(null);
    }

}