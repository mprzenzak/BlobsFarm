package Classes.Character;

import Classes.Map.AMapField;
import Classes.Map.FieldContent;
import Classes.Map.FoodField;
import Classes.Map.WorldMap;
import Interfaces.Live;

import java.util.ArrayList;
import java.util.List;

public class Altruist extends ABlob {
    public static final ArrayList<Integer> AltruistIndcies = new ArrayList<>();
    private static double foodAvailable = 0;
    private boolean alive;
    private boolean immortal;

    public Altruist(int x, int y, boolean alive, String characteristic, int index) {
        super(x, y, alive, characteristic, index);
        this.alive = true;
    }

    @Override
    public void reproduce(int mapWidth, int mapLength) {
        List<Live> objectsOnMap = WorldMap.getObjectsOnMap();
        List<List<Integer>> crowdedFields = WorldMap.getCrowdedFields();
        int aliveBlob = 0;
        for (var blob : objectsOnMap) {
            if (blob != null)
                aliveBlob += 1;
        }
        while (mapLength * mapWidth * 2 > aliveBlob) {
            for (var blob : objectsOnMap) {
                if (blob != null)
                    aliveBlob += 1;
            }
            boolean findNewAltruistCoords = true;
            while (findNewAltruistCoords) {
                int altruistPositionX = (int) (Math.random() * mapWidth);
                int altruistPositionY = (int) (Math.random() * mapLength);
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
                    objectsOnMap.add(new Altruist(altruistPositionX, altruistPositionY, true, "Altruist", index));
                    altruistIndicies.add(index);
                }
            }
        }
    }

//    @Override
//    public void getFieldContent(FieldContent fieldContent) {
//
//    }

    @Override
    public void interactWithLive(Live live) {
        live.setNeighbourType(NeighbourType.ALTRUIST);
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void interactWithAMapField(AMapField field, int mapWidth, int mapLength) {
        List<Integer> foodFieldCoords = WorldMap.getFoodFieldCoords();
        List<Integer> bonusFieldCoords = WorldMap.getBonusFieldCoords();
        List<Integer> trapFieldCoords = WorldMap.getTrapFieldCoords();
        for (int i = 0; i < WorldMap.getFoodFieldCoords().size() - 3; i += 2) {
            if (field != null && field.x == foodFieldCoords.get(i) && field.y == foodFieldCoords.get(i + 1)) {
                switch (neighbourType) {
                    case NONE:
                        foodAvailable = field.sendFood(2);
                    case ALTRUIST:
                        foodAvailable = field.sendFood(1);
                    case AGGRESSOR:
                        foodAvailable = field.sendFood(0.5);
                }
            }
//            } else {
//                FieldContent fieldContent = field.sendFieldContent();
//                switch (fieldContent) {
//                    case EXTENDED_LIFE_LENGTH:
//                        foodAvailable += 1;
//                    case GIVE10CHILDREN:
//                        for (int j = 0; j < 10; j++) {
//                            reproduce();
//                        }
//                    case MAKE_IMMORTAL:
//                        immortal = true;
//                    case TRAP:
//                        die(WorldMap.getObjectsOnMap());
//                }
//            }
        }
        List<Integer> fieldsCoords = WorldMap.getFieldsCoords();
        for (int i = 0; i < fieldsCoords.size(); i += 2) {
            if (field != null && field.x == fieldsCoords.get(i) && field.y == fieldsCoords.get(i + 1)) {
                FieldContent fieldContent = field.sendFieldContent();
                if (fieldContent != null) {
                    switch (fieldContent) {
                        case EXTENDED_LIFE_LENGTH:
                            foodAvailable += 1;
                        case GIVE10CHILDREN:
                            for (int j = 0; j < 10; j++) {
                                reproduce(mapWidth, mapLength);
                            }
                        case MAKE_IMMORTAL:
                            immortal = true;
                        case TRAP:
                            die(WorldMap.getObjectsOnMap());
//                        default:
//                            throw new IllegalStateException("Unexpected valueeeeeeeeee: " + fieldContent);

                    }
                }
            }
        }
        //TODO /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        for (int i = 0; i < WorldMap.getBonusFieldCoords().size(); i += 2) {
//            if (field != null && field.x == bonusFieldCoords.get(i) && field.y == bonusFieldCoords.get(i + 1)) {
//                FieldContent fieldContent = field.sendFieldContent();
//                if (fieldContent != null) {
//                    switch (fieldContent) {
//                        case EXTENDED_LIFE_LENGTH:
//                            foodAvailable += 1;
//                        case GIVE10CHILDREN:
//                            for (int j = 0; j < 10; j++) {
//                                reproduce();
//                            }
//                        case MAKE_IMMORTAL:
//                            immortal = true;
////                        case TRAP:
////                            die(WorldMap.getObjectsOnMap());
//                        default:
//                            throw new IllegalStateException("Unexpected valueeeeeeeeee: " + fieldContent);
//
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < WorldMap.getTrapFieldCoords().size(); i += 2) {
//            if (field != null && field.x == trapFieldCoords.get(i) && field.y == trapFieldCoords.get(i + 1)) {
//                FieldContent fieldContent = field.sendFieldContent();
//                if (fieldContent != null) {
//                //TODO fieldContent is null
//                    switch (fieldContent) {
////                        case EXTENDED_LIFE_LENGTH:
////                            foodAvailable += 1;
////                        case GIVE10CHILDREN:
////                            for (int j = 0; j < 10; j++) {
////                                reproduce();
////                            }
////                        case MAKE_IMMORTAL:
////                            immortal = true;
//                        case TRAP:
//                            if (field.checkIfTrapUsed()) {
//                                die(WorldMap.getObjectsOnMap());
//                                field.markTrapAsUsed();
//                            }
//
//                        default:
//                            throw new IllegalStateException("Unexpected valueeeeeeeeee: " + fieldContent);
//                    }
//                }
//            }
//        }
        //TODO /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (foodAvailable == 2) {
            reproduce(mapWidth, mapLength);
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
