package phase2.Display;

import phase2.ATMTime;
import phase2.Accounts.Account;
import phase2.Transactions.Deposit;
import phase2.People.User;

import java.util.Calendar;

/**
 * The type Deposit display.
 */
class DepositDisplay {

    private static User U = AccountDisplay.U;
    private static Account a = AccountDisplay.a;

    /**
     * The entry point of Deposit Display, access from ATM
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Calendar time = ATMTime.getInstance().getCurrentTime();
        Deposit t = new Deposit("deposits.txt", a, time);
        U.makeTransaction(t);
        AccountDisplay.main(null);
    }

}

