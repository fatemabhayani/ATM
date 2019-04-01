package phase2.Display;

import phase2.People.UserManager;
import phase2.Accounts.Account;
import phase2.People.User;
import phase2.Transactions.*;

import java.util.Scanner;

/**
 * The type Account display.
 */
class AccountDisplay {

    /**
     * The entry point of Account Display.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        User u = UserManager.getUser(args[0]);
        Account a = UserManager.getUserAccount(Integer.valueOf(args[2]));

        System.out.println("Type '0' if you would like to make a transaction." + "\n"
                + "Type '1' if you would like to undo a transaction." + "\n"
                + "Type '2' if you would like to view your most recent transaction." + "\n" +
                " Type '3' to exit.");
        Scanner tmp = new Scanner(System.in);
        String command = tmp.nextLine().replaceAll("//s","");
        while (!(command.equals("0") || command.equals("1") || command.equals("2") || command.equals("3"))) {
            System.out.println("You have not selected a valid action. Try again.");
            command = tmp.nextLine().replaceAll("//s","");
        }
        switch(command) {
            case ("0"):
                System.out.println("Type '0' to make a transfer." + "\n"
                        + "Type '1' to make a deposit." + "\n"
                        + "Type '2' to make a withdrawal." + "\n" +
                        " Type '3' to make a bill payment.");
                command = tmp.nextLine().replaceAll("//s", "");
                while (!(command.equals("0") || command.equals("1") || command.equals("2") || command.equals("3"))) {
                    System.out.println("You have not selected a valid action. Try again.");
                    command = tmp.nextLine().replaceAll("//s", "");
                }
                switch (command) {
                    case ("0"):
                        TransferDisplay.main(args);
                        break;
                    case ("1"):
                        DepositDisplay.main(args);
                        break;
                    case ("2"):
                        WithdrawDisplay.main(args);
                        break;
                    case ("3"):
                        BillDisplay.main(args);
                        break;
                }
            case ("1"):
                if (a.getTransactions().size() > 0) {
                    System.out.println("Type the index of the transaction you wish to undo. 0 represents the most" +
                            " recent transaction.");
                    int number = Integer.valueOf(tmp.nextLine().replaceAll("//s", ""));
                    while (a.getTransactions().size() <= number) {
                        System.out.println("The selected index does not relate to any transaction, please try again.");
                        number = Integer.valueOf(tmp.nextLine().replaceAll("//s", ""));
                    }
                    if (a.getPastTransaction(number).getClass() == Bill.class) {
                        System.out.println("You are not allowed to undo a bill payment.");
                    } else {
                        u.requestUndo(a, number);
                    }
                } else {
                    System.out.println("There are no transactions to be undone.");
                    AccountDisplay.main(args);
                }
            case ("2"):
                if (a.getTransactions().size() > 0) {
                    Transaction t = a.getPastTransaction(0);
                    System.out.println(t.summary());
                    AccountDisplay.main(args);
                } else {
                    System.out.println("There are no transactions to show.");
                    AccountDisplay.main(args);
                }
            case ("3"):
                String[] args1 = new String[1];
                args1[0] = args[0];
                UserDisplay.main(args1);
        }
    }

}

