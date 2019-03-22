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
        Bill t = new Bill(amt, a, ATM.clock.getCurrentTime());
        U.makeBillPayment(t);

        AccountDisplay.main(null);
    }

}