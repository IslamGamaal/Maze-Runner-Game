package models.charcter.decorations;

import models.charcter.Character;

public class Shield extends AbstractDecoration {
    private final int MAX_HEALTH = 50;
    private final int MIN_HEALTH = 0;

    public Shield(final Character character) {
        super(character);
        health = MAX_HEALTH;
    }

    @Override
    protected int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    protected int getMinHealth() {
        return MIN_HEALTH;
    }
}
