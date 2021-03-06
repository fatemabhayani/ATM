package phase2;

import phase2.Tradable.Stock;

import java.util.HashMap;

public class Portfolio {

    public static void main(String[] args) {
        Portfolio p = new Portfolio();
        p.addStock(new Stock("AAPL", 100));
        p.addStock(new Stock("MSFT", 100));
        p.addStock(new Stock("GOOGL", 100));
        System.out.println(p);
    }
    private HashMap<String, Stock> stockTable;
    private HashMap<String, Stock> shortTable;

    public Portfolio(){

        this.stockTable = new HashMap<>();
        this.shortTable = new HashMap<>();
    }

    public void addStock(Stock s){
        if (stockTable.containsKey(s.getName())){
            Stock init = stockTable.get(s.getName());
            init.add(s.getAmount());
        } else {
            stockTable.put(s.getName(), s);
        }

    }

    public double sellStock(String name){
        double value = getValue(name);
        if (value > 0){
            stockTable.remove(name);
        }
        return value;
    }

    public double getValue(String name){
        if (stockTable.containsKey(name)){
            return stockTable.get(name).getTotalValue();
        } else {
            return 0;
        }
    }

    public double getTotalValue(){
        double amount = 0;
        for(Stock s: stockTable.values()){
            amount += s.getTotalValue();
        }
        return amount;
    }

    public void startShort(Stock s){
        s.setShort();
        if (!shortTable.containsKey(s.getName())){
            shortTable.put(s.getName(), s);
        } else {
            System.out.println("You already have a short open with this stock, you are not allowed more");
        }

    }

    public double endShort(String name){
        if (shortTable.containsKey(name)){
            Stock s = shortTable.get(name);
            shortTable.remove(name);
            return (s.getInitPrice() - s.getPrice()) * s.getAmount();
        } else {
            System.out.println("You did not have an open short with this name");
            return 0;
        }
    }
    @Override
    public String toString(){
        return stockTable.toString() + "\t" +shortTable.toString();
    }



}
