package Classes.Map;

import java.util.Random;

/**
 * Enum TrapField represents different bonuses and trap which can be placed on fields on map.
 *
 * @author Mikołaj Przenzak 259066@student.pwr.edu.pl
 */

public enum FieldContent {
    /**
     * Lengthens live period by one day
     */
    EXTENDED_LIFE_LENGTH,
    /**
     * Creates 10 blobs with the same characteristics
     */
    GIVE10CHILDREN,
    /**
     * Makes the blob immortal
     */
    MAKE_IMMORTAL,
    /**
     * Kills the blob
     */
    TRAP;

    /**
     * Draws type of content.
     *
     * @return random FieldContent
     */
    public static FieldContent getRandomBonus() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
