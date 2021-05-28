package Classes.Character;

import Classes.Map.AMapField;
import Classes.Map.FieldContent;
import Classes.Map.WorldMap;
import Interfaces.Live;

import java.util.List;

public class Killer extends ABlob {
    //public int[][] position;

    public Killer(int x, int y, boolean alive, String characteristic) {
        super(x, y, alive, characteristic);
        //interactWithLive();
    }

    @Override
    public void reproduce() {

    }

    @Override
    public void getFieldContent(FieldContent fieldContent) {

    }

    @Override
    public void interactWithLive(Live live) {
        //kill
        List blobs = WorldMap.getObjectsOnMap();
        for (int i = 0; i <blobs.size(); i++) {
            System.out.println(blobs.get(i));
            //if(blobs.get(i)==)
        }
    }

    @Override
    public void die() {

    }

    @Override
    public void isAlive() {

    }

    @Override
    public void interactWithAMapField(AMapField aMapField) {

    }
}
