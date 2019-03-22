package phase2;

import java.util.Scanner;

public class AccountDisplay {

    public static String command;
    public static User U = UserDisplay.U;
    public static Account a = UserDisplay.a;
    public static String accountType = UserDisplay.accountType;

    public static void main(String args[]) {
        System.out.println(a.toString());
        System.out.println("Do you want to request an account (1), make a transaction (2), or exit (3)?");
        Scanner tmp = new Scanner(System.in);
        command = tmp.nextLine();
        command = command.replaceAll("//s","");
        if (command.equals("1")) {
            AccountRequest r = new AccountRequest(U, accountType);
            ATM.b.addRequest(r);
            System.out.println("Your request has been made!");
            AccountDisplay.main(null);
        } else if (command.equals("2")) {
            System.out.println("Do you want to make a transfer (1), deposit (2), withdrawal (3), or bill payment (4)?");
            command = tmp.nextLine();
            command = command.replaceAll("//s","");
            if (command.equals("1")) {
                TransferDisplay.main(null);
            } else if (command.equals("2")) {
                DepositDisplay.main(null);
            } else if (command.equals("3")) {
                WithdrawDisplay.main(null);
            } else {
                BillDisplay.main(null);
            }
        } else {
            UserDisplay.main(null);
        }
    }
}

