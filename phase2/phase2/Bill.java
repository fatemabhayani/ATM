package phase2;

import java.util.Calendar;

public class Bill extends Transaction {

    private Account moneyFrom;

    Bill(ForeignCurrency amount, Account moneyFrom, Calendar date) {
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
    }

    public void undoTransaction() {}

    @Override
    public String toString() {
        return "Pay a bill of " + getAmount();
    }
}

