package Interfaces;

import Classes.Character.NeighbourType;
import Classes.Map.AMapField;

import java.util.List;

public interface Live {
    void reproduce(int mapWidth, int mapLength);

    void interactWithLive(Live live);

    void die(List objectsOnMap);

    void interactWithAMapField(AMapField field, int mapWidth, int mapLength);

    void setCoords(int x, int y);

    int getCoords(String coordinate);

    int getIndex();

    void setNeighbourType(NeighbourType neighbourType);

    boolean isImmortal();

    NeighbourType getNeighbourType();
}
