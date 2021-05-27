package Classes.Map;

import Classes.Character.Agressor;
import Classes.Character.Altruist;
import Classes.Character.Killer;
import Interfaces.Live;

import java.util.ArrayList;
import java.util.List;

public class Map {
    public static AMapField[][] fields;
    public static List<Live> objectsOnMap = new ArrayList<Live>();
    private int x;
    private int y;
    private static List<List<Integer>> usedCoords;
    private static int usedCoordsIndex = 0;

    public Map(int x, int y, int initialFoodAmount) {
        this.x = x;
        this.y = y;
        fields = new AMapField[x][y];
        usedCoords = new ArrayList<List<Integer>>();
        generateFoodFields(initialFoodAmount, x, y);
        generateBonusFields(x, y);
        generateTrapFields(x, y);
        generateCharacters(x, y);
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
            }
        }
    }

    public static void generateCharacters(int x, int y) {
        //generate altruists
        int altruistPositionX = (int) (Math.random() * x);
        int altruistPositionY = (int) (Math.random() * y);
        objectsOnMap.add(new Altruist(altruistPositionX, altruistPositionY,true,"Altruist"));

        //generate agressors
        int agressorsPositionX = (int) (Math.random() * x);
        int agressorsPositionY = (int) (Math.random() * y);
        objectsOnMap.add(new Agressor(agressorsPositionX, agressorsPositionY,true,"Agressor"));

        //generate killers
        int killersPositionX = (int) (Math.random() * x);
        int killersPositionY = (int) (Math.random() * y);
        objectsOnMap.add(new Killer(killersPositionX, killersPositionY,true,"Killer"));
    }
}