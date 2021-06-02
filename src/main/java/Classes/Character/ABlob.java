package Classes.Character;

import Classes.Map.AMapField;
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

    public ABlob(int x, int y, boolean alive, String characteristic) {
        this.x = x;
        this.y = y;
        this.alive = alive;
        this.characteristic = characteristic;
    }

    @Override
    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getCoords(String coordinate){
        if(coordinate == "x")
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
    }

    public void setNeighbourType(NeighbourType neighbourType){
        this.neighbourType = neighbourType;
    }

    @Override
    public void setMapFieldType(AMapField aMapField){
        this.aMapField = aMapField;
    }
}
