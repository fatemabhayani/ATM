package phase2.Display;

import phase2.ATMTime;
import phase2.Accounts.Account;
import phase2.People.UserManager;
import phase2.Tradable.ForeignCurrency;
import phase2.Transactions.Bill;
import phase2.Transactions.Deposit;
import phase2.People.User;

import java.util.Calendar;
import java.util.Scanner;

/**
 * The type Deposit display.
 */
class DepositDisplay {

    /**
     * The entry point of Deposit Display, access from ATM
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println("Make sure you enter a valid cheque!");

        User u = UserManager.getUser(args[0]);
        Account a = UserManager.getUserAccount(Integer.valueOf(args[2]));
        Deposit t = new Deposit("deposits.txt", a, ATMTime.getInstance().getCurrentTime());
        u.makeTransaction(t);

        AccountDisplay.main(args);
    }

}

