package Classes.Character;

import Classes.Map.AMapField;
import Classes.Map.FieldContent;
import Classes.Map.FoodField;
import Classes.Map.WorldMap;
import Interfaces.Live;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Altruist. Represents one of characters. Altruist which meet another altruist on the same field on map,
 * shares food and get 1 piece. If altruist meets ab aggressor, it grabs 0.5 piece of food.
 *
 * @author Mikołaj Przenzak 259066@student.pwr.edu.pl
 */

public class Altruist extends ABlob {
    /**
     * Stores indices of altruists in <code>objectsOnMap</code> list.
     */
    private static final ArrayList<Integer> AltruistIndices = new ArrayList<>();
    /**
     * Stores the amount of food that blob has. If it is equal 2, blob can reproduce, if falls below 1, blob dies.
     */
    private static double foodAvailable = 0;
    /**
     * Defines if blob lives.
     */
    private boolean alive;
    /**
     * Defines if blob can be killed.
     */
    private boolean immortal;

    /**
     * Constructor method. Creates blob of class <code>Altruist</code>.
     *
     * @param x     is first coordinate of blob positioned on map
     * @param y     is second coordinate of blob positioned on map
     * @param alive defines that newly created blob lives
     * @param index defines the position inside the <code>objectsOnMap</code> list
     */
    public Altruist(int x, int y, boolean alive, int index) {
        super(x, y, alive, index);
        this.alive = true;
    }

    @Override
    public void reproduce(int mapWidth, int mapLength, WorldMap map) {
        List<Live> objectsOnMap = map.getObjectsOnMap();
        List<List<Integer>> crowdedFields = map.getCrowdedFields();
        int aliveBlob = 0;
        for (var blob : objectsOnMap) {
            if (blob != null)
                aliveBlob += 1;
        }
        int attemptsCounter = 0;
        if (mapLength * mapWidth * 2 > aliveBlob) {
            boolean findNewAltruistCoords = true;
            while (findNewAltruistCoords) {
                if (attemptsCounter == 1000)
                    break;
                int altruistPositionX = (int) (Math.random() * mapWidth);
                int altruistPositionY = (int) (Math.random() * mapLength);
                int crowd = 0;
                for (List<Integer> list : crowdedFields) {
                    if (list != null & list.get(0) != null && list.get(1) != null) {
                        if (altruistPositionX == list.get(0) && altruistPositionY == list.get(1)) {
                            crowd += 1;
                            if (crowd == 2)
                                attemptsCounter += 1;
                        }
                    }
                }
                if (crowd != 2) {
                    findNewAltruistCoords = false;
                    List<Integer> usedFieldCoords = new ArrayList<Integer>();
                    usedFieldCoords.add(altruistPositionX);
                    usedFieldCoords.add(altruistPositionY);
                    crowdedFields.add(usedFieldCoords);
                    int index = objectsOnMap.size();
                    ArrayList altruistIndicies = Altruist.getAltruistIndices();
                    objectsOnMap.add(new Altruist(altruistPositionX, altruistPositionY, true, index));
                    altruistIndicies.add(index);
                }
            }
        }
    }

    @Override
    public void interactWithLive(Live live, WorldMap map) {
        live.setNeighbourType(NeighbourType.ALTRUIST);
    }

    @Override
    public void interactWithAMapField(AMapField field, int mapWidth, int mapLength, WorldMap map) {
        List<Integer> foodFieldCoords = FoodField.getFoodFieldCoords();
        for (int i = 0; i < FoodField.getFoodFieldCoords().size() - 3; i += 2) {
            if (this.getNeighbourType() != null && field != null && field.getX() == foodFieldCoords.get(i) && field.getY() == foodFieldCoords.get(i + 1)) {
                switch (this.getNeighbourType()) {
                    case NONE:
                        foodAvailable = field.sendFood(2);
                    case ALTRUIST:
                        foodAvailable = field.sendFood(1);
                    case AGGRESSOR:
                        foodAvailable = field.sendFood(0.5);
                }
            }
        }
        List<Integer> fieldsCoords = map.getFieldsCoords();
        for (int i = 0; i < fieldsCoords.size(); i += 2) {
            if (field != null && field.getX() == fieldsCoords.get(i) && field.getY() == fieldsCoords.get(i + 1)) {
                FieldContent fieldContent = field.sendFieldContent();
                if (fieldContent != null) {
                    switch (fieldContent) {
                        case EXTENDED_LIFE_LENGTH:
                            foodAvailable += 1;
                        case GIVE10CHILDREN:
                            for (int j = 0; j < 10; j++) {
                                reproduce(mapWidth, mapLength, map);
                            }
                        case MAKE_IMMORTAL:
                            immortal = true;
                        case TRAP:
                            if (field.checkIfTrapUsed() == false) {
                                die(map.getObjectsOnMap(), map);
                                field.markTrapAsUsed();
                            }
                    }
                }
            }
        }
        if (foodAvailable == 2) {
            reproduce(mapWidth, mapLength, map);
        } else if (foodAvailable < 1) {
            alive = false;
        }
    }

    /**
     * Returns list of indices of altruists contained in <code>objectsOnMap</code> list.
     *
     * @return list of indices <code>objectsOnMap</code>
     */
    public static ArrayList<Integer> getAltruistIndices() {
        return AltruistIndices;
    }

    @Override
    public boolean isImmortal() {
        return immortal;
    }
}
