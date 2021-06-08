package Classes.Character;

import Classes.Map.AMapField;
import Classes.Map.WorldMap;
import Interfaces.Live;

import java.util.List;

public abstract class ABlob implements Live {
    protected int x;
    protected int y;
    protected int index;
    protected AMapField aMapField;
    private boolean alive = true;
    protected String characteristic;
    public NeighbourType neighbourType;

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
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public void die(List objectsOnMap) {
        objectsOnMap.set(index, null);
        WorldMap.updateBlobsAmount(1);
        Live killedBlob = (Live) objectsOnMap.get(index);
        List<List<Integer>> crowdedFields = WorldMap.getCrowdedFields();
        if (killedBlob != null) {
            int x = killedBlob.getCoords("x");
            int y = killedBlob.getCoords("y");
            for (var coords : crowdedFields) {
                if (coords != null && coords.get(0) != null && coords.get(1) != null) {
                    if (coords.get(0) == x && coords.get(1) == y) {
                        coords.set(0, null);
                        coords.set(1, null);
                    }
                }
            }
        }
    }

    public void setNeighbourType(NeighbourType neighbourType) {
        this.neighbourType = neighbourType;
    }

    @Override
    public void setMapFieldType(AMapField aMapField) {
        this.aMapField = aMapField;
    }

    @Override
    public NeighbourType getNeighbourType() {
        return neighbourType;
    }
}
