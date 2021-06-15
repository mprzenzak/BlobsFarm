package Classes.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * Class BonusField represents fields which contain various bonuses.
 *
 * @author Mikołaj Przenzak 259066@student.pwr.edu.pl
 */

public class BonusField extends AMapField {
    /**
     * List of coords of all bonus fields.
     */
    private static List<Integer> bonusFieldCoords = new ArrayList<>();

    /**
     * Constructor method. Creates object of class <code>BonusField</code>.
     *
     * @param x first coordinate of blob positioned on map
     * @param y second coordinate of blob positioned on map
     */
    public BonusField(int x, int y) {
        super(x, y);
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
        FieldContent bonus = FieldContent.getRandomBonus();
        return bonus;
    }

    @Override
    public double sendFood(double amount) {
        return 0;
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
     * Returns list bonusFieldCoords with coordinates of all trap fields.
     *
     * @return list bonusFieldCoords with coordinates of all trap fields.
     */
    public static List<Integer> getBonusFieldCoords() {
        return bonusFieldCoords;
    }
}
