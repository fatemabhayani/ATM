package phase2;

import java.io.FileWriter;
import java.io.Serializable;

/**
 * A cash handling machine in the ATM.
 */
public class CashMachine implements Serializable {

    /**
     * A list of the number of bills in the cash machine.
     *
     * The denominations are in decreasing order (the number of 50 dollar bills is
     * at bills[0], the number of 5 dollar bills is at bills[3]).
     */
    private int[] bills;

    /**
     * Instantiates a new Cash machine.
     */
    public CashMachine(int[] bills) {
        this.bills = bills;
    }

    /**
     * Gets the number of bills in the cash machine of a specified denomination.
     *
     * @param index the index of the denomination in bills
     * @return the number of bills in the cash machine
     */
    private int getNumBills(int index) {
        return bills[index];
    }

    /**
     * Gets the denomination in the cash machine at a specified index of bills.
     *
     * @param index the index of the denomination in bills
     * @return the denomination of the bills
     */
    private int getDenomination(int index) {
        switch (index) {
            case 0: return 50;
            case 1: return 20;
            case 2: return 10;
            case 3: return 5;
            default: return 0;
        }
    }

    /**
     * Increases the number of bills in the cash machine of a specified denomination.
     *
     * @param index the index of the denomination in bills
     * @param count the number of bills to add to the cash machine
     */
    public void increaseBills(int index, int count) {
        bills[index] += count;
    }

    /**
     * Decreases the number of bills in the cash machine of a specified denomination.
     *
     * @param index the index of the denomination in bills
     * @param count the number of bills to remove from the cash machine
     */
    private void decreaseBills(int index, int count) {
        bills[index] -= count;
    }

    /**
     * Writes to alerts.txt if the number of bills of any denomination
     * falls below 20.
     */
    private void writeAlerts() {
        try (FileWriter writer = new FileWriter("phase2/phase2/Data/alerts.txt", true)) {
            for (int i = 0; i < 4; i++) {
                if (getNumBills(i) < 20) {
                    writer.write("The number of " + getDenomination(i) + " dollar bills in the ATM is less than 20.");
                }
            }
        } catch (Exception e) {
            System.out.println("File handling error.");
        }
    }

    /**
     * Withdraws the given the amount from the cash machine by updating the contents
     * of the cash machine.
     *
     * @param amount the amount to be withdrawn from the cash machine
     */
    public void withdrawBills(double amount) {
        int remainder = (int) amount;
        int count;
        for (int i = 0; i < 4; i++) {
            count = numSubtract(remainder, i);
            decreaseBills(i, count);
            remainder -= count * getDenomination(i);
        }

        // Check if the number of bills has gone below 20 for any denomination
        writeAlerts();
    }

    /**
     * Returns the total amount of money in the cash machine.
     *
     * @return the total value of the money in the cash machine
     */
    public int totalBalance() {
        return (5*bills[0] + 10*bills[1] + 20*bills[2] + 50*bills[3]);
    }

    /**
     * Returns the maximum number of bills of a specified denomination that can be
     * withdrawn from the cash machine, with a value less than or equal to amount.
     *
     * @param amount the amount that is being withdrawn from the cash machine
     * @param i the index of the denomination in bills
     * @return the number of bills to withdraw
     */
    private int numSubtract(int amount, int i) {
        int denomination = getDenomination(i);

        // Calculate how many times denomination can be subtracted from amount.
        int count = 0;
        while (amount >= 0) {
            amount -= denomination;
            if (amount >= 0) {
                count += 1;
            }
        }
        return Math.min(count, getNumBills(i));
    }

    @Override
    public String toString() {
        return bills[0] + "/" + bills[1] + "/" + bills[2] + "/" + bills[3];
    }
}
