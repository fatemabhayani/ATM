package phase2;

import java.util.Calendar;

public class DepositDisplay {

    public static User U = AccountDisplay.U;
    public static Account a = AccountDisplay.a;

    public static void main(String[] args) {
        Calendar time = ATM.clock.getCurrentTime();
        Deposit t = new Deposit("deposits.txt", a, time);
        U.makeDeposit(t);
        AccountDisplay.main(null);
    }

}

