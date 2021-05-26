package Interfaces;

import Classes.Map.AMapField;
import Classes.Map.FieldContent;

public interface Live {
    void reproduce();
    void getFieldContent(FieldContent fieldContent);
    void interactWithLive(Live live);
    void die();
    void isAlive();
    void interactWithAMapField(AMapField aMapField);
}
