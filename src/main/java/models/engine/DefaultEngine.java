package models.engine;

public class DefaultEngine extends AbstractEngine {
    public DefaultEngine(){
        super.acceleration = 4;
        super.horizontalFactor = 1;
        super.verticalFactor = 1;
        super.modeRate = 0.25;
    }
}
