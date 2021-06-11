package Classes.Map;

import Classes.Character.Aggressor;
import Classes.Character.Altruist;
import Classes.Character.Killer;
import Classes.Character.NeighbourType;
import Interfaces.Live;

import java.util.ArrayList;
import java.util.List;

public class WorldMap {
    public static AMapField[][] fields;
    public static List<Live> objectsOnMap = new ArrayList<>();
    private int mapWidth;
    private int mapLength;
    private static List<Integer> fieldsCoords = new ArrayList<>();
    private static List<List<Integer>> usedCoords;
    private static List<List<Integer>> crowdedFields = new ArrayList<>();
    private int usedCoordsIndex = 0;
    private static int blobsWhoDiedToday;

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
                        List<Integer> coordsList = new ArrayList<Integer>();
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
                    ArrayList altruistIndicies = Altruist.getAltruistIndicies();
                    objectsOnMap.add(new Altruist(altruistPositionX, altruistPositionY, true, "Altruist", index));
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
                    List<Integer> usedFieldCoords = new ArrayList<Integer>();
                    usedFieldCoords.add(aggressorsPositionX);
                    usedFieldCoords.add(aggressorsPositionY);
                    crowdedFields.add(usedFieldCoords);
                    int index = objectsOnMap.size();
                    ArrayList aggressorIndicies = Aggressor.getAggressorIndicies();
                    objectsOnMap.add(new Aggressor(aggressorsPositionX, aggressorsPositionY, true, "Aggressor", index));
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
                    objectsOnMap.add(new Killer(killerPositionX, killerPositionY, true, "Killer", index));
                }
            }
        }
    }

    public static void mapUpdate(int mapWidth, int mapLength) {
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
                for (int i = 0; i < FoodField.getFoodFieldCoords().size(); i += 2) {
                    if (FoodField.getFoodFieldCoords().get(i) == x && FoodField.getFoodFieldCoords().get(i + 1) == y) {
                        field = fields[x][y];
                    }
                }
                for (int i = 0; i < TrapField.getTrapFieldCoords().size(); i += 2) {
                    if (TrapField.getTrapFieldCoords().get(i) == x && TrapField.getTrapFieldCoords().get(i + 1) == y) {
                        field = fields[x][y];
                    }
                }
                blob.interactWithAMapField(field, mapWidth, mapLength);
            }
        }
        for (var blob : objectsOnMap) {
            if (blob != null) {
                int x = blob.getCoords("x");
                int y = blob.getCoords("y");
                for (var neighbourBlob : objectsOnMap) {
                    if (neighbourBlob != null && neighbourBlob.getCoords("x") == x && neighbourBlob.getCoords("y") == y && blob.getNeighbourType() == NeighbourType.NONE) {
                        neighbourBlob.interactWithLive(blob);
                        blob.interactWithLive(neighbourBlob);
                    }
                }
            }
        }
    }

    public static void updateCoordsAfterBlobDeath(int index) {
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

    public static List<Live> getObjectsOnMap() {
        return objectsOnMap;
    }

    public static List<Integer> getFieldsCoords() {
        return fieldsCoords;
    }

    public static List<List<Integer>> getCrowdedFields() {
        return crowdedFields;
    }

    public static void updateBlobsAmount(int newAmount) {
        if (newAmount == 0) {
            blobsWhoDiedToday = 0;
        } else {
            blobsWhoDiedToday += 1;
        }
    }

    public static int getDiedBlobs() {
        return blobsWhoDiedToday;
    }
}