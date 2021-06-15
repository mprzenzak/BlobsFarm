package Classes.Character;

/**
 * Enum Killer. Stores types of blobs which appear on map. The values are used to set information
 * what is type of blob being located on the same field on map.
 *
 * @author Mikołaj Przenzak 259066@student.pwr.edu.pl
 */
public enum NeighbourType {
    /**
     * Only one blob stands on the field
     */
    NONE,
    /**
     * The field is shared with an aggressor
     */
    AGGRESSOR,
    /**
     * The field is shared with an altruist
     */
    ALTRUIST,
    /**
     * The field is shared with a killer
     */
    KILLER
}