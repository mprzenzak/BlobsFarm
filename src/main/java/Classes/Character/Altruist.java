package Classes.Character;

import Classes.Map.AMapField;
import Classes.Map.FieldContent;
import Classes.Map.WorldMap;
import Interfaces.Live;

import java.util.ArrayList;
import java.util.List;

public class Altruist extends ABlob {
    //indicies of Altruists from objectsOnMap
    public static final ArrayList<Integer> AltruistIndcies = new ArrayList<>();
    private static double foodAvailable = 0;
    private boolean alive;

    public Altruist(int x, int y, boolean alive, String characteristic) {
        super(x, y, alive, characteristic);
        this.alive = true;
    }

    @Override
    public void reproduce() {
        List<Live> objectsOnMap = WorldMap.getObjectsOnMap();
        List<List<Integer>> crowdedFields = WorldMap.getCrowdedFields();
        boolean findNewAltruistCoords = true;
        while (findNewAltruistCoords) {
            int altruistPositionX = (int) (Math.random() * x);
            int altruistPositionY = (int) (Math.random() * y);
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
                objectsOnMap.add(new Altruist(altruistPositionX, altruistPositionY, true, "Altruist"));
                altruistIndicies.add(index);
            }
        }
    }

    @Override
    public void getFieldContent(FieldContent fieldContent) {

    }

    @Override
    public void interactWithLive(Live live) {
        live.setNeighbourType(NeighbourType.ALTRUIST);
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void interactWithAMapField(AMapField field) {
        List<Integer> foodFieldCoords = WorldMap.getFoodFieldCoords();
        List<Integer> bonusFieldCoords = WorldMap.getBonusFieldCoords();
        List<Integer> trapFieldCoords = WorldMap.getTrapFieldCoords();
        for (int i = 0; i < WorldMap.getFoodFieldCoords().size() - 3; i += 2) {
            if (field != null && field.x == foodFieldCoords.get(i) && field.y == foodFieldCoords.get(i + 1))
                switch (neighbourType) {
                    case NONE:
                        foodAvailable = field.sendFood(2);
                    case ALTRUIST:
                        foodAvailable = field.sendFood(1);
                    case AGGRESSOR:
                        foodAvailable = field.sendFood(0.5);
                }
        }
        for (int i = 0; i < WorldMap.getBonusFieldCoords().size(); i += 2) {
            if (field != null && field.x == bonusFieldCoords.get(i) && field.y == bonusFieldCoords.get(i + 1))
                field.sendFieldContent(FieldContent.BONUS_TYPE);
        }
        for (int i = 0; i < WorldMap.getTrapFieldCoords().size(); i += 2) {
            if (field != null && field.x == trapFieldCoords.get(i) && field.y == trapFieldCoords.get(i + 1))
                field.sendFieldContent(FieldContent.TRAP);
        }

        if (foodAvailable == 2) {
            reproduce();
        } else if (foodAvailable < 1) {
            alive = false;
        }
    }

    public static ArrayList<Integer> getAltruistIndicies() {
        return AltruistIndcies;
    }
}
