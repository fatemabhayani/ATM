package phase2.Tradable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Stock {

    public static void main(String[] args) {
        Stock apple = new Stock("AAPL", 700);
        System.out.println(apple.getTotalValue());
    }

    private int amount;
    private String name;

    public Stock(String name, int amount) {
        this.name = name;
        this.amount = amount;

    }

    public double getTotalValue(){
        return getPrice() * amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        try {
            JSONObject json = getStockJSON();
            int x = 0;
            String price = (String) json.get("1. open");
            return Double.valueOf(price);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("There was an error");
            return -1;
        }
    }

    private URL getUrl() throws MalformedURLException {
        String api_key = "BJV700X2EJXM80NP";
        String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + this.name + "&interval=5min&apikey=" + api_key;
        return new URL(urlString);
    }

    private JSONObject getStockJSON() throws ParseException, IOException {
            URL url = getUrl();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String stringJSON = makeJSONString(reader);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(stringJSON);
            JSONObject json1 = (JSONObject) json.get("Time Series (5min)");
            JSONObject metaData = (JSONObject) json.get("Meta Data");
            String mostRecent = (String) metaData.get("3. Last Refreshed");
            int x = 0;
            return (JSONObject) json1.get(mostRecent);
    }

    private String makeJSONString(BufferedReader reader) throws IOException {
        StringBuilder s = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            s.append(line);
            line = reader.readLine();
        }
        reader.close();
        return s.toString();
    }

    @Override
    public String toString() {
        return name + amount;
    }
}
