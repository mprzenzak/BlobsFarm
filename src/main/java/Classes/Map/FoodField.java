package Classes.Map;

import java.util.ArrayList;
import java.util.List;

public class FoodField extends AMapField {
    private static List<Integer> foodFieldCoords = new ArrayList<>();
    private static int foodStorage = 2;

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

    public static List<Integer> getFoodFieldCoords() {
        return foodFieldCoords;
    }
}
