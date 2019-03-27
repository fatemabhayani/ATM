package phase2.Display;

import phase2.Accounts.Account;
import phase2.Transactions.Deposit;
import phase2.People.User;

import java.util.Calendar;

class DepositDisplay {

    private static User U = AccountDisplay.U;
    private static Account a = AccountDisplay.a;

    public static void main(String[] args) {
        Calendar time = ATM.clock.getCurrentTime();
        Deposit t = new Deposit("deposits.txt", a, time);
        U.makeTransaction(t);
        AccountDisplay.main(null);
    }

}

