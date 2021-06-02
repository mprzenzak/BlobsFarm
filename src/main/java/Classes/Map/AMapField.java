package Classes.Map;

import Interfaces.Live;

public abstract class AMapField {
    public int x;
    public int y;

    private FieldContent fieldContent;

    abstract void interactWithLive(Live live);

    abstract void markTrapAsUsed();

    public abstract void sendFieldContent(FieldContent fieldContent);

    public abstract double sendFood(double amount);

}
