package Classes.Map;

import Interfaces.Live;

public class BonusField extends AMapField{
    private static Bonuses bonusType;

    public BonusField(Bonuses bonusType){
        this.bonusType = bonusType;
    }

    @Override
    public void interactWithLive(Live live) {

    }

    @Override
    public void markTrapAsUsed() {

    }

    @Override
    public void sendFieldContent(FieldContent fieldContent) {

    }
}
