package phase2.Transactions;

import phase2.Accounts.Account;
import phase2.Accounts.CreditCard;
import phase2.Accounts.LineOfCredit;
import phase2.Accounts.Savings;
import phase2.ForeignCurrency;
import phase2.WriteFile;


import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class Bill extends Transaction {

    private Account moneyFrom;

    public Bill(ForeignCurrency amount, Account moneyFrom, Calendar date) {
        super(amount, date);
        this.moneyFrom = moneyFrom;
    }

    public boolean transactionApproved() {
        if (moneyFrom.getClass().isInstance(CreditCard.class)) {
            return false;
        } else if (moneyFrom.getClass().isInstance(LineOfCredit.class)) {
            return true;
        } else if (moneyFrom.getClass().isInstance(Savings.class)) {
            return moneyFrom.getBalance().compareTo(getAmount()) > 0;
        } else {
            return moneyFrom.getBalance().compareTo(getAmount()) >= 0 &&
                    moneyFrom.getBalance().compareTo
                            (new ForeignCurrency("CAD", getAmount().
                                    convert("CAD").getAmount() - 100)) >= 0;
        }
    }

    public void makeTransaction() {
        moneyFrom.subtract(amount);
        // writes to outgoing.txt
        recordTransaction();
    }

    private void recordTransaction(){
        try {
            File outoingFile = new File("outgoing.txt"); // outgoing file created
            WriteFile data = new WriteFile(outoingFile, true);

            String record = Double.toString(amount.getAmount()) + " was payed on " + getTimeOfTransaction();
            data.writeToFile(record);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void undoTransaction() {}

    @Override
    public String toString() {
        return "Pay a bill of " + getAmount();
    }
}

