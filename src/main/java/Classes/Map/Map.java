package Classes.Map;

import Interfaces.Live;

import java.util.List;

public class Map {
    public static AMapField[][] fields;
    public static List<Live> objectsOnMap;
    private int x;
    private int y;

    public Map(int x, int y, int initialFoodAmount) {
        this.x = x;
        this.y = y;
        fields = new AMapField[x][y];
        generateFoodFields(initialFoodAmount, x, y);
        generateBonusFields(x, y);
        generateTrapFields(x, y);
    }

    public static void generateFoodFields(int amount, int x, int y) {
        for (int i = 0; i < amount; i++) {
            int foodFieldX = (int) (Math.random() * x);
            int foodFieldY = (int) (Math.random() * y);
            fields[foodFieldX][foodFieldY] = new FoodField(2);
        }
    }

    public static void generateBonusFields(int x, int y) {
        //przyjmuje 5% pól bonusowych
        for (int i = 0; i <= x * y / 20; i++) {
            int bonusFieldX = (int) (Math.random() * x);
            int bonusFieldY = (int) (Math.random() * y);
            Bonuses bonus = Bonuses.getRandomBonus();
            fields[bonusFieldX][bonusFieldY] = new BonusField(bonus);
        }
    }

    public static void generateTrapFields(int x, int y){
        //przyjmuje 5% pól pułapek
        for (int i = 0; i <= x * y / 20; i++) {
            int trapFieldX = (int) (Math.random() * x);
            int trapFieldY = (int) (Math.random() * y);
            fields[trapFieldX][trapFieldY] = new TrapField(false);
        }
    }
}