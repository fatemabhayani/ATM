package phase2.Display;

import phase2.*;
import phase2.Accounts.Account;
import phase2.People.User;
import phase2.People.UserManager;
import phase2.Transactions.Transfer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

class TransferDisplay {

    private static User U = AccountDisplay.U;
    private static Account a = AccountDisplay.a;

    public static void main(String[] args) {
        System.out.println("Is this an internal transaction?");
        Scanner tmp = new Scanner(System.in);

        String command = tmp.nextLine();
        command = command.replaceAll("//s", "");

        if (command.equals("yes")) {
            Account da = null;
            System.out.println("Select the account you want to transfer money to");
            command = tmp.nextLine();
            command = command.replaceAll("//s", "");
            ArrayList<Account> d = U.getAccountList(command);
            if (d.size() == 0) {
                System.out.println("There are no accounts to choose from here! Please try again.");
                AccountDisplay.main(null);
            } else if (d.size() == 1) {
                da = d.get(0);
            } else {
                System.out.println("Enter the number of the account you wish to deposit to: ");
                command = tmp.nextLine();
                int number = Integer.valueOf(command.replaceAll("//s", ""));
                da = d.get(number);
            }

            System.out.println("Enter the sum of money you wish to transfer");
            command = tmp.nextLine();
            int amount = Integer.valueOf(command.replaceAll("//s", ""));
            System.out.println("Enter the three digit currency code of the currency you wish to transfer");
            command = tmp.nextLine();
            command = command.replaceAll("//s", "");
            Calendar time = ATM.clock.getCurrentTime();
            Transfer t = new Transfer(new ForeignCurrency(command, amount), da, a, time);
            U.makeTransfer(t);

        } else {
            System.out.println("Type the username of the user who will receive this transaction.");
            command = tmp.nextLine();
            command = command.replaceAll("//s","");
            User r = UserManager.getUser(command);

            Account da = null;
            ArrayList<Account> account = new ArrayList<>();
            System.out.println("Please select the type of account you want to access");
            System.out.println("Type 'lc' for line of credit account" + "\n" + "Type 'cc' for credit card" +"\n"
                    + "Type 'c' for chequing" +"\n"+"Type 's' for savings");
            command = tmp.nextLine();
            switch(command) {
                case ("lc"): account = r.getAccountList("lc"); break;
                case ("cc"): account = r.getAccountList("cc"); break;
                case ("s"): account = r.getAccountList("s"); break;
                case ("c"): account = r.getAccountList("c"); break;
            }
            if (account.size() == 0) {
                System.out.println("There are no such accounts. Try again");
                TransferDisplay.main(null);
            } else {
                da = account.get(0);
            }

            System.out.println("Enter the sum of money you wish to transfer");
            command = tmp.nextLine();
            int amt = Integer.valueOf(command.replaceAll("//s", ""));
            System.out.println("Enter the three digit currency code of the currency you wish to transfer");
            command = tmp.nextLine();
            command = command.replaceAll("//s", "");
            Calendar time = ATM.clock.getCurrentTime();
            Transfer t = new Transfer(new ForeignCurrency(command, amt), da, a, time);
            U.makeTransfer(t);
        }
        AccountDisplay.main(null);
    }

}
