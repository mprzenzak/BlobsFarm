package Classes.Map;

import Classes.Character.Aggressor;
import Classes.Character.Altruist;
import Classes.Character.Killer;
import Classes.Character.NeighbourType;
import Interfaces.Live;

import java.util.ArrayList;
import java.util.List;

/**
 * Class WorldMap. Generates map of simulation. Blobs can stand on fields on map.
 * Generates any type of field and blob at the beginning of the simulation.
 * In every iteration it refreshes map and finds new coords for blobs.
 *
 * @author Mikołaj Przenzak 259066@student.pwr.edu.pl
 */

public class WorldMap {
    /**
     * Two dimensional array of fields. It's size depends on parameters given by user.
     */
    private AMapField[][] fields;
    /**
     * List of all blobs in simulation.
     */
    private List<Live> objectsOnMap = new ArrayList<>();
    /**
     * Size of map horizontally given by user as a parameter.
     */
    private int mapWidth;
    /**
     * Size of map vertically given by user as a parameter.
     */
    private int mapLength;
    /**
     * Coordinates of fields stored as Integers to iterate through fields and check if blob stand on the field.
     */
    private List<Integer> fieldsCoords = new ArrayList<>();
    /**
     * Coordinates of fields stored as lists of Integers to check if coords are used by any other field
     * and to decide if field can be located here.
     */
    private List<List<Integer>> usedCoords;
    /**
     * Number of blobs on each field. Used to decide if blob can stand on the specified field or if there is no free space.
     */
    private List<List<Integer>> crowdedFields = new ArrayList<>();
    /**
     * An index of <code>usedCoords</code> list used to get the value from specified index.
     */
    private int usedCoordsIndex = 0;
    /**
     * Number of blobs which died in the iteration. Used to count the number of alive blobs.
     */
    private int blobsWhoDiedToday;

    /**
     * Constructor method. Creates map.
     *
     * @param mapWidth                is the horizontal size of map
     * @param mapLength               is the vertical size of map
     * @param initialFoodNumber       number of food fields to be generated
     * @param initialBonusesNumber    number of bonus fields to be generated
     * @param initialTrapsNumber      number of trap fields to be generated
     * @param initialAltruistsNumber  number of altruists to be generated
     * @param initialAggressorsNumber number of aggressors to be generated
     * @param initialKillersNumber    number of killers to be generated
     */
    public WorldMap(int mapWidth, int mapLength, int initialFoodNumber, int initialBonusesNumber, int initialTrapsNumber, int initialAltruistsNumber, int initialAggressorsNumber, int initialKillersNumber) {
        this.mapWidth = mapWidth;
        this.mapLength = mapLength;
        fields = new AMapField[mapWidth][mapLength];
        usedCoords = new ArrayList<>();
        generateFoodFields(initialFoodNumber, mapWidth, mapLength);
        generateBonusFields(mapWidth, mapLength, initialBonusesNumber);
        generateTrapFields(mapWidth, mapLength, initialTrapsNumber);
        generateCharacters(mapWidth, mapLength, initialAltruistsNumber, initialAggressorsNumber, initialKillersNumber);
    }

    /**
     * Generates a number of food fields given as a parameter.
     *
     * @param amount number of food fields to be generated
     * @param x      is the horizontal size of map
     * @param y      is the vertical size of map
     */
    public void generateFoodFields(int amount, int x, int y) {
        for (int i = 0; i < amount; i++) {
            boolean contains = true;
            if (usedCoordsIndex != 0) {
                while (contains == true) {
                    int foodFieldX = (int) (Math.random() * x);
                    int foodFieldY = (int) (Math.random() * y);
                    List<Integer> coordsToCheck = new ArrayList<>();
                    coordsToCheck.add(foodFieldX);
                    coordsToCheck.add(foodFieldY);
                    if (usedCoords.contains(coordsToCheck)) {
                        contains = true;
                    } else {
                        contains = false;
                        fields[foodFieldX][foodFieldY] = new FoodField(foodFieldX, foodFieldY, 2);
                        List<Integer> coordsList = new ArrayList<>();
                        coordsList.add(foodFieldX);
                        coordsList.add(foodFieldY);
                        usedCoords.add(usedCoordsIndex, coordsList);
                        usedCoordsIndex += 1;
                        FoodField.getFoodFieldCoords().add(foodFieldX);
                        FoodField.getFoodFieldCoords().add(foodFieldY);
                        fieldsCoords.add(foodFieldX);
                        fieldsCoords.add(foodFieldY);
                    }
                }
            } else {
                int foodFieldX = (int) (Math.random() * x);
                int foodFieldY = (int) (Math.random() * y);
                fields[foodFieldX][foodFieldY] = new FoodField(foodFieldX, foodFieldY, 2);
                List<Integer> coordsList = new ArrayList<>();
                coordsList.add(foodFieldX);
                coordsList.add(foodFieldY);
                usedCoords.add(0, coordsList);
                usedCoordsIndex += 1;
                FoodField.getFoodFieldCoords().add(foodFieldX);
                FoodField.getFoodFieldCoords().add(foodFieldY);
                fieldsCoords.add(foodFieldX);
                fieldsCoords.add(foodFieldY);
            }
        }
    }

    /**
     * Generates a number of bonus fields given as a parameter.
     *
     * @param x                    is the horizontal size of map
     * @param y                    is the vertical size of map
     * @param initialBonusesNumber number of bonus fields to be generated
     */
    public void generateBonusFields(int x, int y, int initialBonusesNumber) {
        for (int i = 0; i < initialBonusesNumber; i++) {
            boolean contains = true;
            if (usedCoordsIndex != 0) {
                while (contains == true) {
                    int bonusFieldX = (int) (Math.random() * x);
                    int bonusFieldY = (int) (Math.random() * y);
                    List<Integer> coordsToCheck = new ArrayList<>();
                    coordsToCheck.add(bonusFieldX);
                    coordsToCheck.add(bonusFieldY);
                    if (usedCoords.contains(coordsToCheck)) {
                        contains = true;
                    } else {
                        contains = false;
                        fields[bonusFieldX][bonusFieldY] = new BonusField(bonusFieldX, bonusFieldY);
                        List<Integer> coordsList = new ArrayList<>();
                        coordsList.add(bonusFieldX);
                        coordsList.add(bonusFieldY);
                        usedCoords.add(usedCoordsIndex, coordsList);
                        usedCoordsIndex += 1;
                        FoodField.getFoodFieldCoords().add(bonusFieldX);
                        FoodField.getFoodFieldCoords().add(bonusFieldY);
                        fieldsCoords.add(bonusFieldX);
                        fieldsCoords.add(bonusFieldY);
                    }
                }
            } else {
                int bonusFieldX = (int) (Math.random() * x);
                int bonusFieldY = (int) (Math.random() * y);
                fields[bonusFieldX][bonusFieldY] = new BonusField(bonusFieldX, bonusFieldY);
                List<Integer> coordsList = new ArrayList<>();
                coordsList.add(bonusFieldX);
                coordsList.add(bonusFieldY);
                usedCoords.add(0, coordsList);
                usedCoordsIndex += 1;
                FoodField.getFoodFieldCoords().add(bonusFieldX);
                FoodField.getFoodFieldCoords().add(bonusFieldY);
                fieldsCoords.add(bonusFieldX);
                fieldsCoords.add(bonusFieldY);
            }
        }
    }

    /**
     * Generates a number of trap fields given as a parameter.
     *
     * @param x                  is the horizontal size of map
     * @param y                  is the vertical size of map
     * @param initialTrapsNumber number of trap fields to be generated
     */
    public void generateTrapFields(int x, int y, int initialTrapsNumber) {
        for (int i = 0; i < initialTrapsNumber; i++) {
            boolean contains = true;
            if (usedCoordsIndex != 0) {
                while (contains == true) {
                    int trapFieldX = (int) (Math.random() * x);
                    int trapFieldY = (int) (Math.random() * y);
                    List<Integer> coordsToCheck = new ArrayList<>();
                    coordsToCheck.add(trapFieldX);
                    coordsToCheck.add(trapFieldY);
                    if (usedCoords.contains(coordsToCheck)) {
                        contains = true;
                    } else {
                        contains = false;
                        fields[trapFieldX][trapFieldY] = new TrapField(trapFieldX, trapFieldY, false);
                        List<Integer> coordsList = new ArrayList<>();
                        coordsList.add(trapFieldX);
                        coordsList.add(trapFieldY);
                        usedCoords.add(usedCoordsIndex, coordsList);
                        usedCoordsIndex += 1;
                        TrapField.getTrapFieldCoords().add(trapFieldX);
                        TrapField.getTrapFieldCoords().add(trapFieldY);
                        fieldsCoords.add(trapFieldX);
                        fieldsCoords.add(trapFieldY);
                    }
                }
            } else {
                int trapFieldX = (int) (Math.random() * x);
                int trapFieldY = (int) (Math.random() * y);
                fields[trapFieldX][trapFieldY] = new TrapField(trapFieldX, trapFieldY, false);
                fields[trapFieldX][trapFieldY].setFieldContent(FieldContent.TRAP);
                List<Integer> coordsList = new ArrayList<>();
                coordsList.add(trapFieldX);
                coordsList.add(trapFieldY);
                usedCoords.add(0, coordsList);
                usedCoordsIndex += 1;
                TrapField.getTrapFieldCoords().add(trapFieldX);
                TrapField.getTrapFieldCoords().add(trapFieldY);
                fieldsCoords.add(trapFieldX);
                fieldsCoords.add(trapFieldY);
            }
        }
    }

    /**
     * Generates a number of blobs of each type specified as a parameter.
     *
     * @param x                       is the horizontal size of map
     * @param y                       is the vertical size of map
     * @param initialAltruistsNumber  is the number of altruists to be generated
     * @param initialAggressorsNumber is the number of aggressors to be generated
     * @param initialKillersNumber    is the number of killers to be generated
     */
    public void generateCharacters(int x, int y, int initialAltruistsNumber, int initialAggressorsNumber, int initialKillersNumber) {
        for (int i = 0; i < initialAltruistsNumber; i++) {
            boolean findNewAltruistCoords = true;
            while (findNewAltruistCoords) {
                int altruistPositionX = (int) (Math.random() * x);
                int altruistPositionY = (int) (Math.random() * y);
                int crowd = 0;
                for (List<Integer> list : crowdedFields) {
                    if (list != null & list.get(0) != null && list.get(1) != null) {
                        if (altruistPositionX == list.get(0) && altruistPositionY == list.get(1)) {
                            crowd += 1;
                        }
                    }
                }
                if (crowd != 2) {
                    findNewAltruistCoords = false;
                    List<Integer> usedFieldCoords = new ArrayList<>();
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

        for (int i = 0; i < initialAggressorsNumber; i++) {
            boolean findNewAggressorCoords = true;
            while (findNewAggressorCoords) {
                int aggressorsPositionX = (int) (Math.random() * x);
                int aggressorsPositionY = (int) (Math.random() * y);
                int crowd = 0;
                for (List<Integer> list : crowdedFields) {
                    if (list != null & list.get(0) != null && list.get(1) != null) {
                        if (aggressorsPositionX == list.get(0) && aggressorsPositionY == list.get(1)) {
                            crowd += 1;
                        }
                    }
                }
                if (crowd != 2) {
                    findNewAggressorCoords = false;
                    List<Integer> usedFieldCoords = new ArrayList<>();
                    usedFieldCoords.add(aggressorsPositionX);
                    usedFieldCoords.add(aggressorsPositionY);
                    crowdedFields.add(usedFieldCoords);
                    int index = objectsOnMap.size();
                    ArrayList aggressorIndicies = Aggressor.getAggressorIndices();
                    objectsOnMap.add(new Aggressor(aggressorsPositionX, aggressorsPositionY, true, index));
                    aggressorIndicies.add(index);
                }
            }
        }

        for (int i = 0; i < initialKillersNumber; i++) {
            boolean findNewKillerCoords = true;
            while (findNewKillerCoords) {
                int killerPositionX = (int) (Math.random() * x);
                int killerPositionY = (int) (Math.random() * y);
                int crowd = 0;
                for (List<Integer> list : crowdedFields) {
                    if (list != null & list.get(0) != null && list.get(1) != null) {
                        if (killerPositionX == list.get(0) && killerPositionY == list.get(1)) {
                            crowd += 1;
                        }
                    }
                }
                if (crowd != 2) {
                    findNewKillerCoords = false;
                    List<Integer> usedFieldCoords = new ArrayList<>();
                    usedFieldCoords.add(killerPositionX);
                    usedFieldCoords.add(killerPositionY);
                    crowdedFields.add(usedFieldCoords);
                    int index = objectsOnMap.size();
                    objectsOnMap.add(new Killer(killerPositionX, killerPositionY, true, index));
                }
            }
        }
    }

    /**
     * Refreshes positions of blobs in every iteration.
     *
     * @param mapWidth  is the horizontal size of map
     * @param mapLength is the vertical size of map
     * @param map       is an object of <code>WorldMap</code> class
     */
    public void mapUpdate(int mapWidth, int mapLength, WorldMap map) {
        for (var blob : objectsOnMap) {
            if (blob != null)
                blob.setCoords(1000000000, 1000000000);
        }
        for (var field : crowdedFields) {
            field.set(0, 1000000000);
            field.set(1, 1000000000);
        }
        updateBlobsAmount(0);
        for (var list : crowdedFields) {
            list = null;
        }
        for (var blob : objectsOnMap) {
            if (blob != null)
                blob.setNeighbourType(NeighbourType.NONE);
        }
        for (var blob : objectsOnMap) {
            if (blob != null) {
                boolean findNewBlobCoords = true;
                while (findNewBlobCoords) {
                    int positionX = (int) (Math.random() * mapWidth);
                    int positionY = (int) (Math.random() * mapLength);
                    int crowd = 0;
                    for (List<Integer> list : crowdedFields) {
                        if (list != null & list.get(0) != null && list.get(1) != null) {
                            if (positionX == list.get(0) && positionY == list.get(1)) {
                                crowd += 1;
                            }
                        }
                    }
                    if (crowd != 2) {
                        findNewBlobCoords = false;
                        List<Integer> usedFieldCoords = new ArrayList<Integer>();
                        usedFieldCoords.add(positionX);
                        usedFieldCoords.add(positionY);
                        crowdedFields.add(usedFieldCoords);
                        blob.setCoords(positionX, positionY);
                    }
                }
            }
        }
        for (int k = 0; k <= objectsOnMap.size() - 1; k++) {
            var blob = objectsOnMap.get(k);
            if (blob != null) {
                int x = blob.getCoords("x");
                int y = blob.getCoords("y");
                AMapField field = null;
                for (int i = 0; i < FoodField.getFoodFieldCoords().size(); i += 2) {
                    if (FoodField.getFoodFieldCoords().get(i) == x && FoodField.getFoodFieldCoords().get(i + 1) == y) {
                        field = fields[x][y];
                    }
                }
                for (int i = 0; i < BonusField.getBonusFieldCoords().size(); i += 2) {
                    if (BonusField.getBonusFieldCoords().get(i) == x && BonusField.getBonusFieldCoords().get(i + 1) == y) {
                        field = fields[x][y];
                    }
                }
                for (int i = 0; i < TrapField.getTrapFieldCoords().size(); i += 2) {
                    if (TrapField.getTrapFieldCoords().get(i) == x && TrapField.getTrapFieldCoords().get(i + 1) == y) {
                        field = fields[x][y];
                    }
                }
                blob.interactWithAMapField(field, mapWidth, mapLength, this);
            }
        }
        for (var blob : objectsOnMap) {
            if (blob != null) {
                int x = blob.getCoords("x");
                int y = blob.getCoords("y");
                for (var neighbourBlob : objectsOnMap) {
                    if (neighbourBlob != null && neighbourBlob.getCoords("x") == x && neighbourBlob.getCoords("y") == y && blob.getNeighbourType() == NeighbourType.NONE) {
                        neighbourBlob.interactWithLive(blob, map);
                        blob.interactWithLive(neighbourBlob, map);
                    }
                }
            }
        }
    }

    /**
     * Finds the coords where died blob was located and replaces it with null to free it up.
     *
     * @param index index of blob which died
     */
    public void updateCoordsAfterBlobDeath(int index) {
        if (objectsOnMap.get(index) != null) {
            int x = objectsOnMap.get(index).getCoords("x");
            int y = objectsOnMap.get(index).getCoords("y");
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

    /**
     * Returns list od all blobs on map.
     *
     * @return list od all blobs on map
     */
    public List<Live> getObjectsOnMap() {
        return objectsOnMap;
    }

    /**
     * Returns coordinates of all fields on map.
     *
     * @return coordinates of all fields on map
     */
    public List<Integer> getFieldsCoords() {
        return fieldsCoords;
    }

    /**
     * Returns list of numbers of blobs on all fields.
     *
     * @return list of numbers of blobs on all fields
     */
    public List<List<Integer>> getCrowdedFields() {
        return crowdedFields;
    }

    /**
     * Updates amount of alive blobs based on how many of them died and resets the value in every iteration.
     *
     * @param newAmount 0 or 1, 0 if number of died blobs should be reset, 1 if another blob died
     */
    public void updateBlobsAmount(int newAmount) {
        if (newAmount == 0) {
            blobsWhoDiedToday = 0;
        } else {
            blobsWhoDiedToday += 1;
        }
    }

    /**
     * Returns number of blobs which died during the iteration.
     *
     * @return number of blobs which died during the iteration.
     */
    public int getDiedBlobs() {
        return blobsWhoDiedToday;
    }
}