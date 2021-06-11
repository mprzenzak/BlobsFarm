package Classes.Map;

import java.util.ArrayList;
import java.util.List;

public class BonusField extends AMapField {
    private static List<Integer> bonusFieldCoords = new ArrayList<>();

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

    public static List<Integer> getBonusFieldCoords(){
        return bonusFieldCoords;
    }
}
