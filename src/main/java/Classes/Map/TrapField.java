package Classes.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * Class TrapField represents fields which kills blobs when they stands on it.
 *
 * @author Mikołaj Przenzak 259066@student.pwr.edu.pl
 */

public class TrapField extends AMapField {
    /**
     * List of coords of all trap fields.
     */
    private static List<Integer> trapFieldCoords = new ArrayList<>();
    /**
     * Boolean true if trap is used and can't be used again, false when it can be used.
     */
    private boolean isUsed;

    /**
     * Constructor method. Creates object of class <code>TrapField</code>.
     *
     * @param x first coordinate of blob positioned on map
     * @param y second coordinate of blob positioned on map
     * @param isUsed boolean false meaning that trap can kill a blob
     */
    public TrapField(int x, int y, boolean isUsed) {
        super(x, y);
        this.isUsed = isUsed;
        isUsed = false;
    }

    @Override
    public void markTrapAsUsed() {
        isUsed = true;
    }

    @Override
    public FieldContent sendFieldContent() {
        FieldContent trap = FieldContent.TRAP;
        return trap;
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

    @Override
    public boolean checkIfTrapUsed() {
        return isUsed;
    }

    /**
     * Returns list trapFieldCoords with coordinates of all trap fields.
     *
     * @return list trapFieldCoords with coordinates of all trap fields.
     */
    public static List<Integer> getTrapFieldCoords() {
        return trapFieldCoords;
    }
}