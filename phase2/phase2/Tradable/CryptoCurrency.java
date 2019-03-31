package phase2.Tradable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CryptoCurrency extends ForeignCurrency{

    public static void main(String[] args) {
        CryptoCurrency cur = new CryptoCurrency("BTC", 2);
        System.out.println(cur.getRate("BTC", "USD"));
    }


    public CryptoCurrency(String currencyCode, double amount){
        super(currencyCode, amount);
    }



    @Override
    public ForeignCurrency convert(String identifier) {
        double rate = getRate(this.currencyCode, identifier);
        // CryptoCurrencies have different divisibility, so I will take the least divisible which is 8 decimal places
        double amount = this.amount * rate;
        double correctAmount = (double) Math.round(amount * 100000000) / 100000000;
        return new ForeignCurrency(identifier, correctAmount);
    }


    private double getRate(String from, String to){
        try {
            URL url = getCorrectUrl(from, to);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            reader.close();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(line);
            return (double) json.get(to);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    private URL getCorrectUrl(String from, String to) throws MalformedURLException{
        String api_key = "&api_key={bb90d297eba6964b0644e3c38644e1c99b7c6b0e237bd31de47477619857acd2}";
        String urlString = "https://min-api.cryptocompare.com/data/price?fsym=" + from + "&tsyms=" + to + api_key;
        return new URL(urlString);
    }


}
