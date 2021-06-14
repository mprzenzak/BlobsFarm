package Classes.Character;

import Classes.Map.WorldMap;
import Interfaces.Live;

import java.util.List;

public abstract class ABlob implements Live {
    protected int x;
    protected int y;
    protected int index;
    private boolean alive = true;
    protected String characteristic;
    private NeighbourType neighbourType;

    public ABlob(int x, int y, boolean alive, String characteristic, int index) {
        this.x = x;
        this.y = y;
        this.alive = alive;
        this.characteristic = characteristic;
        this.index = index;
    }

    @Override
    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getCoords(String coordinate) {
        if (coordinate == "x")
            return x;
        else
            return y;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void die(List objectsOnMap,WorldMap map) {
        objectsOnMap.set(index, null);
        map.updateBlobsAmount(1);
        map.updateCoordsAfterBlobDeath(index);
    }

    @Override
    public void setNeighbourType(NeighbourType neighbourType) {
        this.neighbourType = neighbourType;
    }

    @Override
    public NeighbourType getNeighbourType() {
        return neighbourType;
    }
}
