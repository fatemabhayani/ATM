package phase2.Display;

import phase2.Accounts.Account;
import phase2.ForeignCurrency;
import phase2.Transactions.Withdraw;
import phase2.People.User;

import java.util.Calendar;
import java.util.Scanner;

class WithdrawDisplay {

    private static User U = AccountDisplay.U;
    private static Account a = AccountDisplay.a;

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