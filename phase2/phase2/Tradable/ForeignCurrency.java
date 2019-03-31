package phase2.Tradable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * The type Foreign currency.
 */
public class ForeignCurrency implements Comparable<Tradable>, Tradable {

    /**
     * The type of currency.
     */
    private String currencyCode;

    /**
     * The amount of currencyCode.
     */
    private double amount;

    /**
     * Instantiates a new Foreign currency.
     *
     * @param currencyCode Capital three digit currency code
     * @param amount       the amount
     */
    public ForeignCurrency(String currencyCode, double amount){
        this.currencyCode = currencyCode;
        this.amount = amount;
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
     * @param t the second Tradable object
     */
    public void add(Tradable t){
        ForeignCurrency d = (ForeignCurrency) t.convert(getCurrencyCode());
        amount += d.getAmount();
    }

    public void add(double amount){
        this.amount += amount;
    }
    /**
     * Subtract two instances for foreign currencies.
     *
     * @param t the second Tradable object.
     */
    public void subtract(Tradable t){
        ForeignCurrency d = (ForeignCurrency) t.convert(getCurrencyCode());
        amount -= d.getAmount();
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


    public int compareTo(Tradable t){
        return Double.compare(amount, t.convert(this.currencyCode).getAmount());
    }

    /**
     * Convert foreign currency.
     *
     * @param currency the currency code
     * @return the foreign currency
     */
    public ForeignCurrency convert(String currency){
        if (currency.equalsIgnoreCase(this.getCurrencyCode())){
            return this;
        }else {
            double rate = getRate(this.currencyCode, currency);
            double newAmount = this.amount * rate;
            return new ForeignCurrency(currency, newAmount);
        }
    }

    private double getRate(String from, String to) {
        if (to.equalsIgnoreCase(from)){
            return 1;
        }
        try {
            URL url = getCorrectUrl(from, to);
            JSONObject rates = getRatesJSON(url);
            if (from.equalsIgnoreCase("EUR")) {
                return (double) rates.get(to);
            } else if (to.equalsIgnoreCase("EUR")) {
                return 1 / (double) rates.get(from);
            } else {
            double fromEuroRate = (double) rates.get(from);
            double toEuroRate = (double) rates.get(to);
            return toEuroRate / fromEuroRate;
        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    private JSONObject getRatesJSON(URL url) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = reader.readLine();
        reader.close();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(line);
        return (JSONObject) json.get("rates");
    }

    private URL getCorrectUrl(String from, String to) throws MalformedURLException {
        URL url;
        if (from.equals("EUR")){
            url = new URL("https://api.exchangeratesapi.io/latest?symbols=" + to);
        } else if (to.equals("EUR")){
            url = new URL("https://api.exchangeratesapi.io/latest?symbols=" + from);
        } else {
            url = new URL("https://api.exchangeratesapi.io/latest?symbols=" + from + "," + to);
        }
        return url;

    }

    @Override
    public String toString() {
        return getAmount() + " " + currencyCode;
    }
}

