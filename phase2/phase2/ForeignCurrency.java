package phase2;

import java.util.Currency;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ForeignCurrency implements Comparable<ForeignCurrency>{

    private Locale locale;
    private double amount;
    private Currency currency;


    public ForeignCurrency(Locale locale, double amount){
        this.locale = locale;
        this.amount = amount;
        this.currency = Currency.getInstance(locale);
    }

    public double getAmount(){
        return amount;
    }

    public Locale getLocale(){
        return locale;
    }

    public void add(ForeignCurrency f){
        if (f.getLocale() == getLocale()){
            amount += f.getAmount();
        } else {
            ForeignCurrency d = f.convert(getLocale());
            amount += d.getAmount();
        }
    }

    public void subtract(ForeignCurrency f){
        if (f.getLocale() == getLocale()){
            amount -= f.getAmount();
        } else {
            ForeignCurrency d = f.convert(getLocale());
            amount -= d.getAmount();
        }
    }

    public void multiply(ForeignCurrency f){
        if (f.getLocale() == getLocale()){
            amount = amount * f.getAmount();
        } else {
            ForeignCurrency d = f.convert(getLocale());
            amount = amount * d.getAmount();
        }
    }

    public ForeignCurrency multiply(double constant){
        amount = amount * constant;
        return this;
    }

    public ForeignCurrency divide(double constant){
        amount = amount / constant;
        return this;
    }

    public void divide(ForeignCurrency f){
        if (f.getLocale() == getLocale()){
            amount = amount / f.getAmount();
        } else {
            ForeignCurrency d = f.convert(getLocale());
            amount = amount / d.getAmount();
        }
    }

    public int compareTo(ForeignCurrency f){
        return Double.compare(amount, this.convert(f.getLocale()).amount);
    }

    public ForeignCurrency convert(Locale location){
        Currency curr = Currency.getInstance(location);
        try {
            URL url = new URL("https://www.xe.com/currencyconverter/convert/?Amount=1&From=" + currency.getCurrencyCode() + "&To=" + curr.getCurrencyCode());
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            if (line.length() > 0) {
                double newAmount = Double.parseDouble(line) * getAmount();
                ForeignCurrency f = new ForeignCurrency(location, newAmount);
                return f;
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return getAmount() + ", " + locale.getISO3Country();
    }
}

