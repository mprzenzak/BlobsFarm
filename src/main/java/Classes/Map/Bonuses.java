package Classes.Map;

import java.util.Random;

public enum Bonuses {
    EXTENDED_LIFE_LENGTH, GIVE10CHILDREN, MAKE_IMMORTAL, CHANGE_CHARACTERICTIC;

    public static Bonuses getRandomBonus() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}

