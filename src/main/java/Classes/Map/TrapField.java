package Classes.Map;

import Interfaces.Live;

public class TrapField extends AMapField {
    private static boolean isUsed = false;

    public TrapField(boolean isUsed){
        this.isUsed = isUsed;
    }

    @Override
    public void interactWithLive(Live live) {

    }

    @Override
    public void markTrapAsUsed() {

    }

    @Override
    public void sendFieldContent(FieldContent fieldContent) {

    }
}
