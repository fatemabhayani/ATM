package phase2;

import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class WithdrawDisplay {

    public static String command;
    public static User U = AccountDisplay.U;
    public static Account a = AccountDisplay.a;

    public static void main(String[] args) {
        Scanner tmp = new Scanner(System.in);
        System.out.println("Enter the sum of money you wish to withdraw.");
        command = tmp.nextLine();
        int amt = Integer.valueOf(command.replaceAll("//s", ""));
        Calendar time = ATM.clock.getCurrentTime();
        Withdraw t = new Withdraw(new ForeignCurrency(Locale.CANADA, amt), a, time);
        U.makeWithdrawal(t);

        AccountDisplay.main(null);
    }

}