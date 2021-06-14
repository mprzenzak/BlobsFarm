package Interfaces;

import Classes.Character.NeighbourType;
import Classes.Map.AMapField;
import Classes.Map.WorldMap;

import java.util.List;

/**
 * Live is interface being inherited by any living character
 *
 * @author Mikołaj Przenzak 259066@student.pwr.edu.pl
 */

public interface Live {
    /**
     * Used for reproduction. Creates a new blob with the same characteristics.
     *
     * @param mapWidth  is first coordinate of blob positioned on map
     * @param mapLength is second coordinate of blob positioned on map
     * @param map       is an object of class <code>WorldMap</code> class
     */
    void reproduce(int mapWidth, int mapLength, WorldMap map);

    /**
     * Sets the type of itself as a neighbour of the blob which is located on the same field of map.
     *
     * @param live is the blob which is located on the same field of map
     * @param map  is an object of class <code>WorldMap</code> class
     */
    void interactWithLive(Live live, WorldMap map);

    /**
     * @param objectsOnMap is the list of blobs located on map
     * @param map          is an object of class <code>WorldMap</code> class
     */
    void die(List objectsOnMap, WorldMap map);

    /**
     * Iterate through fields on map and depending on the type of the field, gives food, applies bonuses and kills.
     *
     * @param field     is a field where the blob is located
     * @param mapWidth  is first coordinate of blob positioned on map
     * @param mapLength is second coordinate of blob positioned on map
     * @param map       is an object of class <code>WorldMap</code> class
     */
    void interactWithAMapField(AMapField field, int mapWidth, int mapLength, WorldMap map);

    /**
     * Sets new position of blob when counted during any new day iteration.
     *
     * @param x is first coordinate of blob positioned on map
     * @param y is second coordinate of blob positioned on map
     */
    void setCoords(int x, int y);

    /**
     * Returns requested coordinate.
     * If x is passed as an argument, it returns the first coordinate of blob positioned on map.
     * If y is passed as an argument, it returns the second coordinate of blob positioned on map.
     *
     * @param coordinate is String, x or y depending on which is required
     * @return value of selected position coordinate
     */
    int getCoords(String coordinate);

    /**
     * Returns index of blob in <code>objectsOnMap</code> list
     *
     * @return index of blob in <code>objectsOnMap</code> list
     */
    int getIndex();

    /**
     * Sets the type of the blob which is located on the same field of map as a neighbour.
     *
     * @param neighbourType is the characteristics of blob which is located on the same field on map.
     */
    void setNeighbourType(NeighbourType neighbourType);

    /**
     * Gives information if blob can be killed.
     *
     * @return boolean true if blob cannot be killed and false if can
     */
    boolean isImmortal();

    /**
     * Returns type of blob which stands on the same field on map.
     *
     * @return type of blob which stands on the same field on map.
     */
    NeighbourType getNeighbourType();
}
