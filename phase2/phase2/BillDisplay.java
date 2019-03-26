package phase2;

import java.util.Scanner;

public class BillDisplay {

    public static String command;
    public static User U = AccountDisplay.U;
    public static Account a = AccountDisplay.a;

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