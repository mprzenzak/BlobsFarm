package Interfaces;

import Classes.Character.NeighbourType;
import Classes.Map.AMapField;
import Classes.Map.FieldContent;

import java.util.List;

public interface Live {
    void reproduce(int mapWidth, int mapLength);

//    void getFieldContent(FieldContent fieldContent);

    void interactWithLive(Live live);

    void die(List objectsOnMap);

    boolean isAlive();

    void interactWithAMapField(AMapField field, int mapWidth, int mapLength);

    void setCoords(int x, int y);

    int getCoords(String coordinate);

    void setMapFieldType(AMapField field);

    void setIndex(int index);

    int getIndex();

    void setNeighbourType(NeighbourType neighbourType);

    boolean isImmortal();

    NeighbourType getNeighbourType();
}
