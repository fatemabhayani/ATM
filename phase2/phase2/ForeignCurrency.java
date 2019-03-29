package phase2;

import java.util.Currency;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * The type Foreign currency.
 */
public class ForeignCurrency implements Comparable<ForeignCurrency>{

    private String currencyCode;
    private double amount;
    private Currency currency;


    /**
     * Instantiates a new Foreign currency.
     *
     * @param currencyCode Capital three digit currency code
     * @param amount       the amount
     */
    public ForeignCurrency(String currencyCode, double amount){
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.currency = Currency.getInstance(currencyCode);
    }

    /**
     * Get amount double.
     *
     * @return the amount
     */
    public double getAmount(){
        return amount;
    }

    /**
     * Get currency code string.
     *
     * @return the three letter string currency code
     */
    public String getCurrencyCode(){
        return currencyCode;
    }

    /**
     * Add two instances for foreign currencies.
     *
     * @param f the second ForeignCurrency
     */
    public void add(ForeignCurrency f){
        if (f.getCurrencyCode().equals(getCurrencyCode())){
            amount += f.getAmount();
        } else {
            ForeignCurrency d = f.convert(getCurrencyCode());
            amount += d.getAmount();
        }
    }
    /**
     * Subtract two instances for foreign currencies.
     *
     * @param f the second ForeignCurrency
     */
    public void subtract(ForeignCurrency f){
        if (f.getCurrencyCode().equals(getCurrencyCode())){
            amount -= f.getAmount();
        } else {
            ForeignCurrency d = f.convert(getCurrencyCode());
            amount -= d.getAmount();
        }
    }


    /**
     * Multiply foreign currency.
     *
     * @param constant the constant
     * @return the foreign currency
     */
    public ForeignCurrency multiply(double constant){
        amount = amount * constant;
        return this;
    }


    public int compareTo(ForeignCurrency f){
        return Double.compare(amount, this.convert(f.getCurrencyCode()).amount);
    }

    /**
     * Convert foreign currency.
     *
     * @param cur the currency code
     * @return the foreign currency
     */
    public ForeignCurrency convert(String cur){
        Currency curr = Currency.getInstance(cur);
        try {
            URL url = new URL("https://www.xe.com/currencyconverter/convert/?Amount=1&From=" + currency.getCurrencyCode() + "&To=" + curr.getCurrencyCode());
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            if (line.length() > 0) {
                double newAmount = Double.parseDouble(line) * getAmount();
                return new ForeignCurrency(cur, newAmount);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ForeignCurrency("CAD", 777);
    }

    @Override
    public String toString() {
        return getAmount() + " " + currency.getCurrencyCode();
    }
}

