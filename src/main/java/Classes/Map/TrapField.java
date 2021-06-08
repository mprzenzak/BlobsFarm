package Classes.Map;

import Interfaces.Live;

public class TrapField extends AMapField {
    private static boolean isUsed;

    public TrapField(boolean isUsed) {
        this.isUsed = isUsed;
        isUsed = false;
    }

    @Override
    public void interactWithLive(Live live) {

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
    public boolean checkIfTrapUsed(){
        return isUsed;
    }
}
