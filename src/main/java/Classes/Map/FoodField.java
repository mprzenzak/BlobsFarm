package Classes.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * Class FoodField represents fields which contain food.
 *
 * @author Mikołaj Przenzak 259066@student.pwr.edu.pl
 */

public class FoodField extends AMapField {
    /**
     * List of coords of all food fields.
     */
    private static List<Integer> foodFieldCoords = new ArrayList<>();
    /**
     * Amount of food stored in this field. Initial value is 2.
     */
    private static int foodStorage = 2;

    /**
     * Constructor method. Creates object of class <code>FoodField</code>.
     *
     * @param x           first coordinate of blob positioned on map
     * @param y           second coordinate of blob positioned on map
     * @param foodStorage amount of food stored by field
     */
    public FoodField(int x, int y, int foodStorage) {
        super(x, y);
        this.foodStorage = foodStorage;
    }

    @Override
    public void markTrapAsUsed() {

    }

    @Override
    public boolean checkIfTrapUsed() {
        return false;
    }

    @Override
    public FieldContent sendFieldContent() {
        return null;
    }

    @Override
    public double sendFood(double amount) {
        return amount;
    }

    @Override
    public void setFieldContent(FieldContent fieldContent) {

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    /**
     * Returns list foodFieldCoords with coordinates of all food fields.
     *
     * @return list foodFieldCoords with coordinates of all food fields.
     */
    public static List<Integer> getFoodFieldCoords() {
        return foodFieldCoords;
    }
}
