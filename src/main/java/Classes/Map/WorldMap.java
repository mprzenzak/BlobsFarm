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
    public static List<Live> objectsOnMap = new ArrayList<Live>();
    private static int mapWidth;
    private static int mapLength;
    private static List<Integer> foodFieldCoords = new ArrayList<Integer>();
    private static List<Integer> bonusFieldCoords = new ArrayList<Integer>();
    private static List<Integer> trapFieldCoords = new ArrayList<Integer>();
    private static List<List<Integer>> usedCoords;
    private static List<List<Integer>> crowdedFields = new ArrayList<List<Integer>>();
    private static int usedCoordsIndex = 0;

    public WorldMap(int mapWidth, int mapLength, int initialFoodAmount, int initialAltruistsNumber, int initialAggressorsNumber, int initialKillersNumber) {
        this.mapWidth = mapWidth;
        this.mapLength = mapLength;
        fields = new AMapField[mapWidth][mapLength];
        usedCoords = new ArrayList<List<Integer>>();
        generateFoodFields(initialFoodAmount, mapWidth, mapLength);
        generateBonusFields(mapWidth, mapLength);
        generateTrapFields(mapWidth, mapLength);
        generateCharacters(mapWidth, mapLength, initialAltruistsNumber, initialAggressorsNumber, initialKillersNumber);
    }

    public static void generateFoodFields(int amount, int x, int y) {
        for (int i = 0; i < amount; i++) {
            boolean contains = true;
            if (usedCoordsIndex != 0) {
                while (contains == true) {
                    int foodFieldX = (int) (Math.random() * x);
                    int foodFieldY = (int) (Math.random() * y);
                    if (usedCoords.contains(new int[]{foodFieldX, foodFieldY})) {
                        contains = true;
                    } else {
                        contains = false;
                        fields[foodFieldX][foodFieldY] = new FoodField(2);
                        //usedCoords.get(usedCoordsIndex).set(foodFieldX, foodFieldY);
                        List<Integer> coordsList = new ArrayList<Integer>();
                        coordsList.add(foodFieldX);
                        coordsList.add(foodFieldY);
                        usedCoords.add(usedCoordsIndex, coordsList);
                        usedCoordsIndex += 1;
                        foodFieldCoords.add(foodFieldX);
                        foodFieldCoords.add(foodFieldY);
                    }
                }
            } else {
                int foodFieldX = (int) (Math.random() * x);
                int foodFieldY = (int) (Math.random() * y);
                fields[foodFieldX][foodFieldY] = new FoodField(2);
                List<Integer> coordsList = new ArrayList<Integer>();
                coordsList.add(foodFieldX);
                coordsList.add(foodFieldY);
                usedCoords.add(0, coordsList);
                //get(0).set(foodFieldX, foodFieldY);
                usedCoordsIndex += 1;
                foodFieldCoords.add(foodFieldX);
                foodFieldCoords.add(foodFieldY);
            }
        }
    }

    public static void generateBonusFields(int x, int y) {
        //przyjmuje 5% pól bonusowych
        for (int i = 0; i <= x * y / 20; i++) {
            boolean contains = true;
            if (usedCoordsIndex != 0) {
                while (contains == true) {
                    int bonusFieldX = (int) (Math.random() * x);
                    int bonusFieldY = (int) (Math.random() * y);
                    Bonuses bonus = Bonuses.getRandomBonus();
                    if (usedCoords.contains(new int[]{bonusFieldX, bonusFieldY})) {
                        contains = true;
                    } else {
                        contains = false;
                        fields[bonusFieldX][bonusFieldY] = new BonusField(bonus);
                        List<Integer> coordsList = new ArrayList<Integer>();
                        coordsList.add(bonusFieldX);
                        coordsList.add(bonusFieldY);
                        usedCoords.add(usedCoordsIndex, coordsList);
                        usedCoordsIndex += 1;
                        bonusFieldCoords.add(bonusFieldX);
                        bonusFieldCoords.add(bonusFieldY);
                    }
                }
            } else {
                int bonusFieldX = (int) (Math.random() * x);
                int bonusFieldY = (int) (Math.random() * y);
                Bonuses bonus = Bonuses.getRandomBonus();
                fields[bonusFieldX][bonusFieldY] = new BonusField(bonus);
                List<Integer> coordsList = new ArrayList<Integer>();
                coordsList.add(bonusFieldX);
                coordsList.add(bonusFieldY);
                usedCoords.add(0, coordsList);
                usedCoordsIndex += 1;
                bonusFieldCoords.add(bonusFieldX);
                bonusFieldCoords.add(bonusFieldY);
            }
        }
    }

    public static void generateTrapFields(int x, int y) {
        //przyjmuje 5% pól pułapek
        for (int i = 0; i <= x * y / 20; i++) {
            boolean contains = true;
            if (usedCoordsIndex != 0) {
                while (contains == true) {
                    int trapFieldX = (int) (Math.random() * x);
                    int trapFieldY = (int) (Math.random() * y);
                    if (usedCoords.contains(new int[]{trapFieldX, trapFieldY})) {
                        contains = true;
                    } else {
                        contains = false;
                        fields[trapFieldX][trapFieldY] = new TrapField(false);
                        List<Integer> coordsList = new ArrayList<Integer>();
                        coordsList.add(trapFieldX);
                        coordsList.add(trapFieldY);
                        usedCoords.add(usedCoordsIndex, coordsList);
                        usedCoordsIndex += 1;
                        trapFieldCoords.add(trapFieldX);
                        trapFieldCoords.add(trapFieldY);
                    }
                }
            } else {
                int trapFieldX = (int) (Math.random() * x);
                int trapFieldY = (int) (Math.random() * y);
                fields[trapFieldX][trapFieldY] = new TrapField(false);
                List<Integer> coordsList = new ArrayList<Integer>();
                coordsList.add(trapFieldX);
                coordsList.add(trapFieldY);
                usedCoords.add(0, coordsList);
                usedCoordsIndex += 1;
                trapFieldCoords.add(trapFieldX);
                trapFieldCoords.add(trapFieldY);
            }
        }
    }

    public static void generateCharacters(int x, int y, int initialAltruistsNumber, int initialAggressorsNumber, int initialKillersNumber) {
        //generate altruists
        for (int i = 0; i < initialAltruistsNumber; i++) {
            boolean findNewAltruistCoords = true;
            while (findNewAltruistCoords) {
                int altruistPositionX = (int) (Math.random() * x);
                int altruistPositionY = (int) (Math.random() * y);
                int crowd = 0;
                for (List<Integer> list : crowdedFields) {
                    if (altruistPositionX == list.get(0) && altruistPositionY == list.get(1)) {
                        crowd += 1;
                    }
                }
                if (crowd != 2) {
                    findNewAltruistCoords = false;
                    List<Integer> usedFieldCoords = new ArrayList<Integer>();
                    usedFieldCoords.add(altruistPositionX);
                    usedFieldCoords.add(altruistPositionY);
                    crowdedFields.add(usedFieldCoords);
                    int index = objectsOnMap.size();
                    ArrayList altruistIndicies = Altruist.getAltruistIndicies();
                    objectsOnMap.add(new Altruist(altruistPositionX, altruistPositionY, true, "Altruist"));
                    altruistIndicies.add(index);
                }
            }
        }

        for (int i = 0; i < initialAggressorsNumber; i++) {
            //generate aggressors
            boolean findNewAggressorCoords = true;
            while (findNewAggressorCoords) {
                int aggressorsPositionX = (int) (Math.random() * x);
                int aggressorsPositionY = (int) (Math.random() * y);
                int crowd = 0;
                for (List<Integer> list : crowdedFields) {
                    if (aggressorsPositionX == list.get(0) && aggressorsPositionY == list.get(1)) {
                        crowd += 1;
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
                    objectsOnMap.add(new Aggressor(aggressorsPositionX, aggressorsPositionY, true, "Aggressor"));
                    aggressorIndicies.add(index);
                }
            }
        }

        for (int i = 0; i < initialKillersNumber; i++) {
            //generate aggressors
            boolean findNewKillerCoords = true;
            while (findNewKillerCoords) {
                int killerPositionX = (int) (Math.random() * x);
                int killerPositionY = (int) (Math.random() * y);
                int crowd = 0;
                for (List<Integer> list : crowdedFields) {
                    if (killerPositionX == list.get(0) && killerPositionY == list.get(1)) {
                        crowd += 1;
                    }
                }
                if (crowd != 2) {
                    findNewKillerCoords = false;
                    List<Integer> usedFieldCoords = new ArrayList<Integer>();
                    usedFieldCoords.add(killerPositionX);
                    usedFieldCoords.add(killerPositionY);
                    crowdedFields.add(usedFieldCoords);
                    objectsOnMap.add(new Killer(killerPositionX, killerPositionY, true, "Killer"));
                }
            }
        }
    }

    public static void mapUpdate() {
        for(var list : crowdedFields){
            list = null;
        }
        for (var blob : objectsOnMap) {
//            blob.setNeighbourType(NeighbourType.NONE);
//            int positionX = (int) (Math.random() * mapWidth);
//            int positionY = (int) (Math.random() * mapLength);
//            blob.setCoords(positionX, positionY);
            boolean findNewBlobCoords = true;
            while (findNewBlobCoords) {
                int positionX = (int) (Math.random() * mapWidth);
                int positionY = (int) (Math.random() * mapLength);
                int crowd = 0;
                for (List<Integer> list : crowdedFields) {
                    if (positionX == list.get(0) && positionY == list.get(1)) {
                        crowd += 1;
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
        for (var blob : objectsOnMap) {
            blob.interactWithLive(blob);
            int x = blob.getCoords("x");
            int y = blob.getCoords("y");
            AMapField field = null;
            for (int i = 0; i < foodFieldCoords.size(); i += 2) {
                if (foodFieldCoords.get(i) == x && foodFieldCoords.get(i + 1) == y) {
                    field = fields[x][y];
                }
            }
            for (int i = 0; i < bonusFieldCoords.size(); i += 2) {
                if (bonusFieldCoords.get(i) == x && bonusFieldCoords.get(i + 1) == y) {
                    field = fields[x][y];
                }
            }
            for (int i = 0; i < trapFieldCoords.size(); i += 2) {
                if (trapFieldCoords.get(i) == x && trapFieldCoords.get(i + 1) == y) {
                    field = fields[x][y];
                }
            }
            //blob.setMapFieldType(FoodField);
            blob.interactWithAMapField(field);
        }
    }

    public static List<Live> getObjectsOnMap() {
        return objectsOnMap;
    }

    public static List<Integer> getFoodFieldCoords() {
        return foodFieldCoords;
    }

    public static List<Integer> getBonusFieldCoords() {
        return bonusFieldCoords;
    }

    public static List<Integer> getTrapFieldCoords() {
        return trapFieldCoords;
    }
}