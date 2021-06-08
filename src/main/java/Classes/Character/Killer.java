package Classes.Character;

import Classes.Map.AMapField;
import Classes.Map.FieldContent;
import Classes.Map.WorldMap;
import Interfaces.Live;

import java.util.List;

public class Killer extends ABlob {
    public Killer(int x, int y, boolean alive, String characteristic, int index) {
        super(x, y, alive, characteristic, index);
    }

//    @Override
//    public void getFieldContent(FieldContent fieldContent) {
//
//    }

    @Override
    public void reproduce(int mapWidth, int mapLength) {

    }

    @Override
    public void interactWithLive(Live live) {
        live.setNeighbourType(NeighbourType.KILLER);
        List<Live> blobs = WorldMap.getObjectsOnMap();
        for (var blob : blobs) {
            if (blob != null && blob.getIndex() == this.index) {
                if (!blob.isImmortal()) {
                    blob.die(blobs);
                }
            }
        }
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public void interactWithAMapField(AMapField field, int mapWidth, int mapLength) {
        List<Integer> foodFieldCoords = WorldMap.getFoodFieldCoords();
        List<Integer> bonusFieldCoords = WorldMap.getBonusFieldCoords();
        List<Integer> trapFieldCoords = WorldMap.getTrapFieldCoords();
//        for (int i = 0; i < WorldMap.getFoodFieldCoords().size() - 3; i += 2) {
//            if (field != null && field.x == foodFieldCoords.get(i) && field.y == foodFieldCoords.get(i + 1))
//                switch (neighbourType) {
//                    case NONE:
//                        field.sendFood(2);
//                    case ALTRUIST:
//                        field.sendFood(1.5);
//                    case AGGRESSOR:
//                        field.sendFood(0);
//                }
//        }
//        for (int i = 0; i < WorldMap.getBonusFieldCoords().size(); i += 2) {
//            if (field != null && field.x == bonusFieldCoords.get(i) && field.y == bonusFieldCoords.get(i + 1))
//                field.sendFieldContent(FieldContent.BONUS);
//        }
//        for (int i = 0; i < WorldMap.getTrapFieldCoords().size(); i += 2) {
//            if (field != null && field.x == trapFieldCoords.get(i) && field.y == trapFieldCoords.get(i + 1))
//                field.sendFieldContent(FieldContent.TRAP);
//        }
    }

    @Override
    public boolean isImmortal() {
        return false;
    }
}
