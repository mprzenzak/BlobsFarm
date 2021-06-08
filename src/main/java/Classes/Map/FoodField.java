package Classes.Map;

import Interfaces.Live;

public class FoodField extends AMapField {
    private static int foodStorage = 2;

    public FoodField(int foodStorage) {
        this.foodStorage = foodStorage;
    }

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
        return null;
    }

    @Override
    public double sendFood(double amount) {
        return amount;
    }

    @Override
    public void setFieldContent(FieldContent fieldContent) {

    }
}
