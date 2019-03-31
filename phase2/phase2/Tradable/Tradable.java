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
     * Subtract two tradable objects.
     *
     * @param t the tradable object
     */
    void subtract(Tradable t);

    /**
     * Multiply tradable.
     *
     * @param constant the constant to be multiplied
     * @return the tradable object
     */
    Tradable multiply(double constant);

    /**
     * Convert tradable.
     *
     * @param identifier the 3 digit identifier of the tradable object
     * @return the tradable
     */
    Tradable convert(String identifier);

    String toString();
}
