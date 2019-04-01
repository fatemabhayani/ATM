package phase2.Accounts;

import phase2.People.User;
import phase2.Portfolio;
import phase2.Tradable.ForeignCurrency;
import phase2.Tradable.Stock;
import phase2.Transactions.Transaction;
import phase2.Transactions.Withdraw;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Account for buying and selling stocks (bought and sold in USD by nature of the api used in Stock class)
 */
public class InvestmentAccount extends AssetAccount {



    private Portfolio portfolio = new Portfolio();

    public InvestmentAccount(Calendar date, User owner1, int num ){
        super(date, owner1, num);
        this.balance = new ForeignCurrency("USD", 0);
    }


    public void subtract(Transaction t){
        if (t instanceof Withdraw ){
            subtract(t.getAmount());
        }
    }

    public void subtract(ForeignCurrency f){
        if (balance.compareTo(f) >= 0){
            balance.subtract(f);
        } else {
            System.out.println("You don't have enough money in your account to do this");
        }
    }

    public double getStockPrice(String name){
        Stock s = new Stock(name, 1);
        return s.getPrice();
    }


    public void buyStock(String name, int volume){
        Stock s = new Stock(name, volume);
        if (balance.getAmount() > s.getTotalValue()){
            balance.subtract(s.getTotalValue());
            portfolio.addStock(s);
        } else {
            System.out.println("You did not have enough money to buy this stock");
        }
    }

    public void sellStock(String name){
        double value = portfolio.sellStock(name);
        this.balance.add(value);
    }

    public void startShort(String name, int amount){
        Stock s = new Stock(name, amount);
        portfolio.startShort(s);
    }

    public void endShort(String name){
        double diff = portfolio.endShort(name);
        balance.add(diff);
    }

    public String portfolioString(){
        return portfolio.toString();
    }




}
