package phase2.Tradable;

/**
 * The interface Tradable, object that can be traded in the ATM.
 */
public interface Tradable{

    /**
     * Gets amount.
     *
     * @return the amount
     */
    double getAmount();

    /**
     * Add two tradable objects.
     *
     * @param t the tradable obejct
     */
    void add(Tradable t);

    /**
     * Add a double to a tradable object.
     *
     * @param amount the amount to add
     */
    void add(double amount);

    String getCurrencyCode();

    int compareTo(Tradable t);

    /**
     * Subtract two tradable objects.
     *
     * @param t the tradable object
     */
    void subtract(Tradable t);

    /**
     * Subtract a double to a tradable object.
     *
     * @param amount the amount to add
     */
    void subtract(double amount);


    /**
     * Multiply tradable.
     *
     * @param constant the constant to be multiplied
     * @return the tradable object
     */
    ForeignCurrency multiply(double constant);

    /**
     * Convert tradable.
     *
     * @param identifier the 3 digit identifier of the tradable object
     * @return the tradable
     */
    ForeignCurrency convert(String identifier);

    String toString();
}
