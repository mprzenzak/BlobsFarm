package Classes.Character;

import Classes.Map.AMapField;
import Classes.Map.WorldMap;
import Interfaces.Live;

import java.util.List;

public class Killer extends ABlob {
    public Killer(int x, int y, boolean alive, String characteristic, int index) {
        super(x, y, alive, characteristic, index);
    }

    @Override
    public void reproduce(int mapWidth, int mapLength, WorldMap map) {

    }

    @Override
    public void interactWithLive(Live live, WorldMap map) {
        live.setNeighbourType(NeighbourType.KILLER);
        List<Live> blobs = map.getObjectsOnMap();
        for (var blob : blobs) {
            if (blob != null && blob.getIndex() == this.index) {
                if (!blob.isImmortal()) {
                    blob.die(blobs, map);
                }
            }
        }
    }

    @Override
    public void interactWithAMapField(AMapField field, int mapWidth, int mapLength, WorldMap map) {

    }

    @Override
    public boolean isImmortal() {
        return false;
    }
}
