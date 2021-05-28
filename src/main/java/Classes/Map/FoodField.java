package Classes.Map;

import Interfaces.Live;

import java.util.List;

public class FoodField extends AMapField {
    private static int foodStorage = 2;

    public FoodField(int foodStorage) {
        this.foodStorage = foodStorage;
    }

    public void removeFood() {

    }

    public void sendFood() {

    }

    @Override
    public void interactWithLive(Live live) {
        //check blob presence
//        List blobs = WorldMap.getObjectsOnMap();
//        for (int i = 0; i <blobs.size(); i++) {
//            //if(blobs.get(i)[0])
//        }
    }

    @Override
    public void markTrapAsUsed() {

    }

    @Override
    public void sendFieldContent(FieldContent fieldContent) {

    }
}
