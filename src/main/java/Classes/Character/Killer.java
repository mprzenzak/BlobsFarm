package Classes.Character;

import Classes.Map.AMapField;
import Classes.Map.FieldContent;
import Interfaces.Live;

public class Killer extends ABlob {
    //public int[][] position;

    public Killer(int x, int y, boolean alive, String characteristic) {
        super(x, y, alive, characteristic);
    }

    @Override
    public void reproduce() {

    }

    @Override
    public void getFieldContent(FieldContent fieldContent) {

    }

    @Override
    public void interactWithLive(Live live) {

    }

    @Override
    public void die() {

    }

    @Override
    public void isAlive() {

    }

    @Override
    public void interactWithAMapField(AMapField aMapField) {

    }
}
