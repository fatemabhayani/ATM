package phase2.Display;

import phase2.ATMTime;
import phase2.Accounts.Account;
import phase2.Data.DataSaver;
import phase2.People.BankManager;
import phase2.People.User;
import phase2.Tradable.ForeignCurrency;
import phase2.Transactions.*;

import java.util.Calendar;
import java.util.Scanner;

class ATMController {

    ATMController() {}

    void incorrectAns(Scanner scanner, String inUse) {
        while (!(inUse.toLowerCase().equals("no") || inUse.toLowerCase().equals("yes"))) {
            System.out.println("You did not give a valid answer, try again.");
            inUse = scanner.nextLine();
        }
    }

    void verifyBankManager(Scanner sc) {
        System.out.println("Please input your password.");
        String command = sc.nextLine().replaceAll("//s", "").toLowerCase();

        while (!(command.equals(BankManager.getInstance().getPassword()))) {
            System.out.println("You did not give a valid password, try again! If you would like to go back to " +
                    "the login screen, type 'exit'.");
            command = sc.nextLine().toLowerCase();
            if (command.equals("exit")) {
                ATM.main(null);
            }
        }
        ManagerDisplay.main(null);
    }

    void logOut() {
        DataSaver s = new DataSaver();
        s.writeAll();
        System.exit(0);
    }

}
