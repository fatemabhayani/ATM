package phase2;

import java.util.Currency;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ForeignCurrency {

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

    public void add(Locale location, double money){
        if (location == getLocale()){
            amount += money;
        } else {
        }
    }

    public String compare(Locale location, double money){
        if (this.convert(location).amount == money){
            return "=";
        } else if (this.convert(location).amount > money){
            return ">";
        } else{
            return "<";
        }
    }

    public ForeignCurrency convert(Locale location){
        Currency curr = Currency.getInstance(location);
        try {
            URL url = new URL("https://www.xe.com/currencyconverter/convert/?Amount=1&From=" + currency.getCurrencyCode() + "&To=" + curr.getCurrencyCode());
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            if (line.length() > 0) {
                double newAmount = Double.parseDouble(line) * getAmount();
                ForeignCurrency f = new ForeignCurrency(locale, newAmount);
                return f;
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
