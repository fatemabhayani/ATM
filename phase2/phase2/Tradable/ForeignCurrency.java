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
public class ForeignCurrency implements Comparable<ForeignCurrency> {

    /**
     * The type of currency.
     */
    String currencyCode;

    /**
     * The amount of currencyCode.
     */
    double amount;

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
     * @param f the second currency object
     */
    public void add(ForeignCurrency f){
        ForeignCurrency d =  f.convert(getCurrencyCode());
        amount += d.getAmount();
    }

    public void add(double amount){
        this.amount += amount;
    }
    /**
     * Subtract two instances for foreign currencies.
     *
     * @param f the second currency object.
     */
    public void subtract(ForeignCurrency f){
        ForeignCurrency d =  f.convert(getCurrencyCode());
        amount -= d.getAmount();
    }

    public void subtract(double amount){
        this.amount -= amount;
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
        return Double.compare(amount, f.convert(this.currencyCode).getAmount());
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
            double newAmount = (this.amount * rate);
            // for fiat currencies the lowest denomination is the 2nd decimal place, ex 1 cent
            double correctNewAmount = (double) Math.round(newAmount * 100) / 100;
            return new ForeignCurrency(currency, correctNewAmount);
        }
    }

    /**
     *
     * @param from the currency we are converting
     * @param to the currency we will convert it into
     * @return the new value
     */
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

    /**
     *
     * @param url is a url
     * @return the exchange rate
     */
    private JSONObject getRatesJSON(URL url) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = reader.readLine();
        reader.close();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(line);
        return (JSONObject) json.get("rates");
    }

    /**
     *
     * @param from the currency we are converting
     * @param to the currency we will convert it into
     * @return the correct url
     */
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

