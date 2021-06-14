package Interfaces;

import Classes.Character.NeighbourType;
import Classes.Map.AMapField;
import Classes.Map.WorldMap;

import java.util.List;

public interface Live {
    void reproduce(int mapWidth, int mapLength, WorldMap map);

    void interactWithLive(Live live,WorldMap map);

    void die(List objectsOnMap, WorldMap map);

    void interactWithAMapField(AMapField field, int mapWidth, int mapLength, WorldMap map);

    void setCoords(int x, int y);

    int getCoords(String coordinate);

    int getIndex();

    void setNeighbourType(NeighbourType neighbourType);

    boolean isImmortal();

    NeighbourType getNeighbourType();
}
