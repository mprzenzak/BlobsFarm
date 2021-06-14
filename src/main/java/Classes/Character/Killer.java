package Classes.Character;

import Classes.Map.AMapField;
import Classes.Map.WorldMap;
import Interfaces.Live;

import java.util.List;

/**
 * Class Killer. Represents one of characters. Killer which meet another blob of any type which is located on the same
 * field on map, kills this blob.
 *
 * @author Mikołaj Przenzak 259066@student.pwr.edu.pl
 */

public class Killer extends ABlob {

    /**
     * Constructor method. Creates blob of class <code>Killer</code>.
     *
     * @param x     is first coordinate of blob positioned on map
     * @param y     is second coordinate of blob positioned on map
     * @param alive defines that newly created blob lives
     * @param index defines the position inside the <code>objectsOnMap</code> list
     */
    public Killer(int x, int y, boolean alive, int index) {
        super(x, y, alive, index);
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
