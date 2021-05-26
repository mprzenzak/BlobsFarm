package Classes.Map;

import Interfaces.Live;

public abstract class AMapField {
    private FieldContent fieldContent;

    abstract void interactWithLive(Live live);

    abstract void markTrapAsUsed();

    abstract void sendFieldContent(FieldContent fieldContent);

}
