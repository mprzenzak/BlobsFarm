package Classes.Character;

import Interfaces.Live;

public abstract class ABlob implements Live {
    protected int x;
    protected int y;
    private boolean alive = true;
    protected String characteristic;

    public ABlob(int x, int y, boolean alive, String characteristic) {
        this.x = x;
        this.y = y;
        this.alive = alive;
        this.characteristic = characteristic;
    }
}
