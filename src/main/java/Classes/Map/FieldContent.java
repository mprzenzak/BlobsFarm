package Classes.Map;

import java.util.Random;

public enum FieldContent {
    EXTENDED_LIFE_LENGTH, GIVE10CHILDREN, MAKE_IMMORTAL, TRAP;

    public static FieldContent getRandomBonus() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
