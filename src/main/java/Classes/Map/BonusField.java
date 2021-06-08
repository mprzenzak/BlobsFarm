package Classes.Map;

import Interfaces.Live;

public class BonusField extends AMapField {
    @Override
    public void interactWithLive(Live live) {

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
}
