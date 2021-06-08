package Classes.Character;

import Classes.Map.AMapField;
import Classes.Map.Bonuses;
import Classes.Map.FieldContent;
import Classes.Map.WorldMap;
import Interfaces.Live;

import java.util.ArrayList;
import java.util.List;

public class Aggressor extends ABlob {
    public static final ArrayList<Integer> AggressorIndcies = new ArrayList<>();
    private static double foodAvailable = 0;
    private static Bonuses bonus = null;
    private boolean alive;
    private boolean immortal;

    public Aggressor(int x, int y, boolean alive, String characteristic, int index) {
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
        int attemptsCounter = 0;
        if (mapLength * mapWidth * 2 > aliveBlob) {
//            for (var blob : objectsOnMap) {
//                if (blob != null)
//                    aliveBlob += 1;
//            }
            boolean findNewAggressorCoords = true;
            while (findNewAggressorCoords) {
                if (attemptsCounter == 1000)
                    break;
                int aggressorsPositionX = (int) (Math.random() * mapWidth);
                int aggressorsPositionY = (int) (Math.random() * mapLength);
                int crowd = 0;
                for (List<Integer> list : crowdedFields) {
                    if (list != null & list.get(0) != null && list.get(1) != null) {
                        if (aggressorsPositionX == list.get(0) && aggressorsPositionY == list.get(1)) {
                            crowd += 1;
                            if (crowd == 2)
                                attemptsCounter += 1;
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
    }

//    @Override
//    public void getFieldContent(FieldContent fieldContent) {
//
//    }

    @Override
    public void interactWithLive(Live live) {
        live.setNeighbourType(NeighbourType.AGGRESSOR);
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
            if (neighbourType != null && field != null && field.x == foodFieldCoords.get(i) && field.y == foodFieldCoords.get(i + 1)) {
                switch (neighbourType) {
                    case NONE:
                        foodAvailable = field.sendFood(2);
                    case ALTRUIST:
                        foodAvailable = field.sendFood(1.5);
                    case AGGRESSOR:
                        foodAvailable = field.sendFood(0);
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
        //TODO ////////////////////////////////////////////////////////////////////////////////////////////////
//        for (int i = 0; i < WorldMap.getBonusFieldCoords().size(); i += 2) {
//            if (field != null && field.x == bonusFieldCoords.get(i) && field.y == bonusFieldCoords.get(i + 1)) {
//                FieldContent fieldContent = field.sendFieldContent();
//                //if (fieldContent != null) {
//                switch (fieldContent) {
//                    case EXTENDED_LIFE_LENGTH:
//                        foodAvailable += 1;
//                    case GIVE10CHILDREN:
//                        for (int j = 0; j < 10; j++) {
//                            reproduce();
//                        }
//                    case MAKE_IMMORTAL:
//                        immortal = true;
////                        case TRAP:
////                            if (field.checkIfTrapUsed()) {
////                                die(WorldMap.getObjectsOnMap());
////                                field.markTrapAsUsed();
//                        //   }
//                        //}
//                    default:
//                        throw new IllegalStateException("Unexpected valueeeeeeeeee: " + fieldContent);
//
//                }
//            }
//        }
//        for (int i = 0; i < WorldMap.getTrapFieldCoords().size(); i += 2) {
//            if (field != null && field.x == trapFieldCoords.get(i) && field.y == trapFieldCoords.get(i + 1)) {
//                FieldContent fieldContent = field.sendFieldContent();
//                //if (fieldContent != null) {
//                switch (fieldContent) {
////                        case EXTENDED_LIFE_LENGTH:
////                            foodAvailable += 1;
////                        case GIVE10CHILDREN:
////                            for (int j = 0; j < 10; j++) {
////                                reproduce();
////                            }
////                        case MAKE_IMMORTAL:
////                            immortal = true;
//                    case TRAP:
//                        die(WorldMap.getObjectsOnMap());
//                        //}
//                    default:
//                        throw new IllegalStateException("Unexpected valueeeeeeeeee: " + fieldContent);
//
//                }
//            }
//        }
        //TODO ////////////////////////////////////////////////////////////////////////////////////////////////
//        FieldContent fieldContent = field.sendFieldContent();
//        switch (fieldContent) {
//            case EXTENDED_LIFE_LENGTH:
//                foodAvailable += 1;
//            case GIVE10CHILDREN:
//                for (int i = 0; i < 10; i++) {
//                    reproduce();
//                }
//            case MAKE_IMMORTAL:
//                immortal = true;
//            case TRAP:
//                die(WorldMap.getObjectsOnMap());
//                field.markTrapAsUsed();
//        }
        if (foodAvailable == 2) {
            reproduce(mapWidth, mapLength);
        } else if (foodAvailable < 1) {
            alive = false;
        }
    }

    public static ArrayList<Integer> getAggressorIndicies() {
        return AggressorIndcies;
    }

    @Override
    public boolean isImmortal() {
        return immortal;
    }
}
