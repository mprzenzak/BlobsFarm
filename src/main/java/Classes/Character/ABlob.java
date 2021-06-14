package Classes.Character;

import Classes.Map.WorldMap;
import Interfaces.Live;

import java.util.List;

/**
 * ABlob is an abstract class to represent a model od blob.
 *
 * @author Mikołaj Przenzak 259066@student.pwr.edu.pl
 */

public abstract class ABlob implements Live {
    /**
     * First coordinate of blob positioned on map.
     */
    protected int x;
    /**
     * Second coordinate of blob positioned on map.
     */
    protected int y;
    /**
     * Index of blob in the <code>objectsOnMap</code> list which is list of all living characters.
     */
    protected int index;
    /**
     * Boolean to check if blob is alive.
     */
    private boolean alive = true;

    private NeighbourType neighbourType;

    public ABlob(int x, int y, boolean alive, int index) {
        this.x = x;
        this.y = y;
        this.alive = alive;
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
