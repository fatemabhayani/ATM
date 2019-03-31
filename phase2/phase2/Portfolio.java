package phase2;

import java.util.HashMap;

public class Portfolio {

    public static void main(String[] args) {
        Portfolio p = new Portfolio();
        p.addStock("AAPL", 100);
        System.out.println(p.stockTable);
    }
    private HashMap<String, Integer> stockTable;

    Portfolio(){
        this.stockTable = new HashMap<>();
    }

    public void addStock(String name, int amount){
        if (stockTable.containsKey(name)){
            Integer num = stockTable.get(name);
            num += amount;
            stockTable.replace(name, num);
        } else {
            stockTable.put(name, amount);
        }

    }


}
