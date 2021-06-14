package Classes.Character;

import Classes.Map.AMapField;
import Classes.Map.FieldContent;
import Classes.Map.FoodField;
import Classes.Map.WorldMap;
import Interfaces.Live;

import java.util.ArrayList;
import java.util.List;

public class Altruist extends ABlob {
    private static final ArrayList<Integer> AltruistIndcies = new ArrayList<>();
    private static double foodAvailable = 0;
    private boolean alive;
    private boolean immortal;

    public Altruist(int x, int y, boolean alive, String characteristic, int index) {
        super(x, y, alive, characteristic, index);
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
                    ArrayList altruistIndicies = Altruist.getAltruistIndicies();
                    objectsOnMap.add(new Altruist(altruistPositionX, altruistPositionY, true, "Altruist", index));
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

    public static ArrayList<Integer> getAltruistIndicies() {
        return AltruistIndcies;
    }

    @Override
    public boolean isImmortal() {
        return immortal;
    }
}
