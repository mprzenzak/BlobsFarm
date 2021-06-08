package Classes.Map;

import Interfaces.Live;

public abstract class AMapField {
    public int x;
    public int y;

    private FieldContent fieldContent;

    abstract void interactWithLive(Live live);

    public abstract void markTrapAsUsed();

    public abstract boolean checkIfTrapUsed();

    public abstract FieldContent sendFieldContent();

    public abstract double sendFood(double amount);

    public abstract void setFieldContent(FieldContent fieldContent);
}
