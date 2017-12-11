package models.mazeObjects.gift;

import models.mazeObjects.Host;
import models.mazeObjects.Visitor;

import java.awt.*;

public class HealthGift  extends Gift implements Visitor{
    private int type;
    public HealthGift(int type, Point pos) {
        if(type > 0 && type < 6) {
            assignGiftType(type);
        }
        else {
            throw new RuntimeException();
        }
        this.setPosition(pos.x, pos.y);
    }
    @Override
    public void assignGiftType(int giftType) {
        this.type = giftType;
    }

    @Override
    public boolean affectHealthBy(int effect) {
        throw new RuntimeException();
    }

    @Override
    public void affectAmmo(int ammo) {
        throw new RuntimeException();
    }

    @Override
    public void visit(Host host) {
        host.affectHealthBy(type * 20);
    }
}