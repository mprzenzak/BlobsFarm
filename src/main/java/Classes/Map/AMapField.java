package Classes.Map;

/**
 * Class AMapField is an abstract class to represent a model od field of map.
 *
 * @author Mikołaj Przenzak 259066@student.pwr.edu.pl
 */

public abstract class AMapField {
    /**
     * First coordinate of blob positioned on map
     */
    protected int x;
    /**
     * Second coordinate of blob positioned on map
     */
    protected int y;

    /**
     * Constructor method.
     *
     * @param x first coordinate of blob positioned on map
     * @param y second coordinate of blob positioned on map
     */
    public AMapField(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the value of boolean ifUsed to true so trap would not be used next time.
     */
    public abstract void markTrapAsUsed();

    /**
     * Checks if trap can be used.
     *
     * @return boolean true if it can be used and false if can't
     */
    public abstract boolean checkIfTrapUsed();

    /**
     * Sends what the field stores to blob.
     *
     * @return FieldContent, type of what does the field store
     */
    public abstract FieldContent sendFieldContent();

    /**
     * Sends specified amount of food to blob.
     *
     * @param amount amount to be send
     * @return double, amount of food
     */
    public abstract double sendFood(double amount);

    /**
     * Sends what does field contain to blob. (Bonuses and trap)
     *
     * @param fieldContent bonus type or trap
     */
    public abstract void setFieldContent(FieldContent fieldContent);

    /**
     * Returns first coordinate of blob positioned on map
     *
     * @return first coordinate of blob positioned on map
     */
    public abstract int getX();

    /**
     * Returns first coordinate of blob positioned on map
     *
     * @return first coordinate of blob positioned on map
     */
    public abstract int getY();
}
