package models.mazeObjects.bomb;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import models.Observer.Observed;
import models.Observer.Observer;
import models.charcter.AliveObject;
import models.charcter.LifeObserver;
import models.engine.Matter;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;
import views.Drawable;
import views.flyweight.BombImage;
import views.flyweight.BoxImage;
import views.flyweight.ExplosionImage;

import java.awt.*;
import java.awt.geom.Point2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bomb extends Drawable implements Bomb_I, Visitor, Host, Matter, AliveObject, Observed {

    private int type;
    private int damageRate;
    private int timer;
    private Point2D position;
    private long explodeTime;
    private int health = 1;
    private long triggeringStartTime;
    private boolean isCovered = true;
    private List<Observer> observers;
    private boolean isExploded = false;

    public Bomb(int type, Point pos) {
        if (type == 0) {
            type = 1;
        }
        this.type = type;
        setDamageRate(-20 * type);
        if (getDamageRate() == 0) {
            setDamageRate(-20);
        }
        setTimer(type);
        if (getTimer() > 5) {
            setTimer(5);
        }
        setPosition(pos.x, pos.y);
        observers = new ArrayList<>();
    }

    @Override
    public int getDamageRate() {
        return this.damageRate;
    }

    @Override
    public void setDamageRate(int damageValue) {
        this.damageRate = damageValue;
    }

    @Override
    public int getTimer() {
        return this.timer;
    }

    @Override
    public void setTimer(int time) {
        this.timer = time;
    }

    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public int getVelocity() {
        return 0;
    }

    @Override
    public void setVelocity(int velocity) {

    }

    @Override
    public int getAcceleration() {
        return 0;
    }

    @Override
    public void setAcceleration(int acceleration) {
    }

    @Override
    public void setPosition(final double x, final double y) {
        this.position = new Point2D.Double(x, y);
    }

    @Override
    public void explode() {
        isExploded = true;
        final URL resource = getClass().getResource("/music/bombSound.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        //todo el music ahe, uncomment
    }

    @Override
    public long getExplodeTime() {
        return this.explodeTime;
    }

    @Override
    public void setExplodeTime(long explodeTime) {
        this.explodeTime = explodeTime;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public boolean affectHealthBy(int effect) {
        this.health = 0;
        if (this.isCovered) {
            this.isCovered = false;
        } else {
            explode();
        }
        return true;
    }

    @Override
    public void destroy() {
        observers.stream().forEach(n -> ((LifeObserver) n).notifyFuneralOf(Bomb.this));
    }

    @Override
    public void revive() {
        observers.stream().forEach(n -> ((LifeObserver) n).notifyResurrectionOf(Bomb.this));
    }

    @Override
    public void visit(Host host) {
        if (isExploded)
            return;
        try {
            if (!isCovered) {
                AliveObject aliveObject = (AliveObject) host;
                aliveObject.affectHealthBy(this.getDamageRate());
                explode();
            }
        } catch (ClassCastException e) {
            //TODO handle object is not alive
        }

    }

    @Override
    public Image getImage() {
        if (this.isCovered) {
            return BoxImage.getImage();
        } else {
            if (!isExploded) {
                return BombImage.getImage();
            } else {
                return getExplosionImage();
            }
        }
    }

    private Image getExplosionImage() {
        Image image = ExplosionImage.getImage();
        super.imageWidth = (int) image.getWidth();
        super.setAnimated(true, 31);
        super.setCyclicAnimation(false);
        super.setCoordinates();
        return image;
    }

    @Override
    public void finishAnimation() {
        super.finishAnimation();
        destroy();
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public boolean canObserve(final Observer observer) {
        return observer instanceof LifeObserver;
    }
}