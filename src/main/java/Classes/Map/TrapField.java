package Classes.Map;

import java.util.ArrayList;
import java.util.List;

public class TrapField extends AMapField {
    private static List<Integer> trapFieldCoords = new ArrayList<>();
    private static boolean isUsed;

    public TrapField(int x, int y,boolean isUsed) {
        super(x,y);
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
    public boolean checkIfTrapUsed(){
        return isUsed;
    }

    public static List<Integer> getTrapFieldCoords(){
        return trapFieldCoords;
    }
}