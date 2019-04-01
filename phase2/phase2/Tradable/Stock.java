package phase2.Tradable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Stock {


    private final String name;
    private final double initPrice;
    private int amount;
    private boolean isShort = false;


    public Stock(String name, int amount) {
        this.name = name;
        this.amount = amount;
        this.initPrice = getPrice(name);
    }

    public void setShort(){
        this.isShort = true;
    }

    public double getInitPrice(){
        return this.initPrice;
    }

    public double getTotalValue(){
        return this.getPrice() * amount;
    }

    public void add(int amount){
        if (!isShort){
            this.amount += amount;
        }
    }

    public int getAmount(){
        return this.amount;
    }

    public void subtract(int amount){
        if (!isShort){
            this.amount -= amount;
        }
    }

    public static double getValue(Stock s){
        return getPrice(s.name) * s.amount;
    }


    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return getPrice(this.name);
    }

    public static double getPrice(String name) {
        try {
            JSONObject json = getStockJSON(name);
            int x = 0;
            String price = (String) json.get("1. open");
            return Double.valueOf(price);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("There was an error");
            return -1;
        }
    }


    private static URL getUrl(String name) throws MalformedURLException {
        String api_key = "BJV700X2EJXM80NP";
        String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + name + "&interval=5min&apikey=" + api_key;
        return new URL(urlString);
    }

    private static JSONObject getStockJSON(String name) throws ParseException, IOException {
            URL url = getUrl(name);
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

    private static String makeJSONString(BufferedReader reader) throws IOException {
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
        return name + " " + amount ;
    }
}
