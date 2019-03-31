package phase2.Tradable;

public class CryptoCurrency implements Comparable<Tradable>, Tradable {

    private String currencyCode;

    private double amount;

    public CryptoCurrency(String currencyCode, double amount){
        this.currencyCode = currencyCode;
        this.amount = amount;
    }

    public void subtract(double d){
    }


    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void add(Tradable t) {
        CryptoCurrency d = (CryptoCurrency) t.convert(getCurrencyCode());
        amount += d.getAmount();
    }

    @Override
    public void add(double amount){
        this.amount += amount;
    }

    @Override
    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public int compareTo(Tradable o) {
        return Double.compare(amount, o.convert(this.currencyCode).getAmount());
    }

    @Override
    public void subtract(Tradable t) {
        CryptoCurrency d = (CryptoCurrency) t.convert(getCurrencyCode());
        amount -= d.getAmount();
    }

    @Override
    public Tradable multiply(double constant) {
        amount = amount * constant;
        return this;
    }

    @Override
    public Tradable convert(String identifier) {
        return this;
        //TODO: implement this
    }

    public String toString() {
        return getAmount() + " " + currencyCode;
    }
}