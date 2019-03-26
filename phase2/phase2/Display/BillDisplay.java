package phase2.Display;

import phase2.Accounts.Account;
import phase2.ForeignCurrency;
import phase2.Transactions.Bill;
import phase2.People.User;

import java.util.Scanner;

class BillDisplay {

    private static String command;
    private static User U = AccountDisplay.U;
    private static Account a = AccountDisplay.a;

    public static void main(String[] args) {
        Scanner tmp = new Scanner(System.in);
        System.out.println("Enter the sum of money you wish to pay:");
        command = tmp.nextLine();
        double amt = Double.valueOf(command.replaceAll("//s", ""));
        System.out.println("Enter the three digit currency code of the currency you wish to pay the bill in");
        command = tmp.nextLine();
        command = command.replaceAll("//s", "");
        Bill t = new Bill(new ForeignCurrency(command, amt), a, ATM.clock.getCurrentTime());
        U.makeBillPayment(t);

        AccountDisplay.main(null);
    }

}