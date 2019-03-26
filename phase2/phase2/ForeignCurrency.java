package phase2;

import java.util.Currency;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ForeignCurrency implements Comparable<ForeignCurrency>{

    private String currencyCode;
    private double amount;
    private Currency currency;


    public ForeignCurrency(String currencyCode, double amount){
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.currency = Currency.getInstance(currencyCode);
    }

    public double getAmount(){
        return amount;
    }

    public String getCurrencyCode(){
        return currencyCode;
    }

    public void add(ForeignCurrency f){
        if (f.getCurrencyCode().equals(getCurrencyCode())){
            amount += f.getAmount();
        } else {
            ForeignCurrency d = f.convert(getCurrencyCode());
            amount += d.getAmount();
        }
    }

    public void subtract(ForeignCurrency f){
        if (f.getCurrencyCode().equals(getCurrencyCode())){
            amount -= f.getAmount();
        } else {
            ForeignCurrency d = f.convert(getCurrencyCode());
            amount -= d.getAmount();
        }
    }


    public ForeignCurrency multiply(double constant){
        amount = amount * constant;
        return this;
    }


    public int compareTo(ForeignCurrency f){
        return Double.compare(amount, this.convert(f.getCurrencyCode()).amount);
    }

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
        return null;
    }

    @Override
    public String toString() {
        return getAmount() + ", " + currency.getCurrencyCode();
    }
}

