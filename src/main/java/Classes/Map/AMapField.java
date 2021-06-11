package Classes.Map;

public abstract class AMapField {
    protected int x;
    protected int y;

    public AMapField(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void markTrapAsUsed();

    public abstract boolean checkIfTrapUsed();

    public abstract FieldContent sendFieldContent();

    public abstract double sendFood(double amount);

    public abstract void setFieldContent(FieldContent fieldContent);

    public abstract int getX();

    public abstract int getY();
}
