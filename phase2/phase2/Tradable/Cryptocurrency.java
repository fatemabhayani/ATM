package phase2.Tradable;

public class Cryptocurrency implements Comparable<Tradable>, Tradable {

    private String currencyCode;

    private double amount;

    public Cryptocurrency(String currencyCode, double amount){
        this.currencyCode = currencyCode;
        this.amount = amount;
    }


    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void add(Tradable t) {
        Cryptocurrency d = (Cryptocurrency) t.convert(getCurrencyCode());
        amount += d.getAmount();
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
        Cryptocurrency d = (Cryptocurrency) t.convert(getCurrencyCode());
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
