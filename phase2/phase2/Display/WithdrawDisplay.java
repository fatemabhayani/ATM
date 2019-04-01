package phase2.Display;

import phase2.ATMTime;
import phase2.Accounts.Account;
import phase2.People.UserManager;
import phase2.Tradable.ForeignCurrency;
import phase2.Transactions.Bill;
import phase2.Transactions.Withdraw;
import phase2.People.User;

import java.util.Calendar;
import java.util.Scanner;

/**
 * The type Withdraw display.
 */
class WithdrawDisplay {

    /**
     * The entry point of Withdraw Display, access through ATM
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        User u = UserManager.getUser(args[0]);
        Account a = UserManager.getUserAccount(Integer.valueOf(args[2]));
        System.out.println(a == null);

        Scanner tmp = new Scanner(System.in);
        System.out.println("Enter the sum of money you wish to withdraw:");
        String command = tmp.nextLine();
        int amt = Integer.valueOf(command.replaceAll("//s", ""));
        Withdraw t = new Withdraw(new ForeignCurrency("CAD", amt), a, ATMTime.getInstance().getCurrentTime());
        u.makeTransaction(t);

        AccountDisplay.main(args);
    }

}