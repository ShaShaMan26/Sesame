package Sesame.Objects;

import Sesame.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObj extends Obj {
    protected Point destination = null;
    protected double velocity = 0;
    protected double realX;
    protected double realY;
    protected Rectangle hitBox = new Rectangle();
    protected boolean hitBoxVisible = false;

    protected GameObj(int x, int y) {
        super(x, y);
    }
    protected GameObj(BufferedImage sprite) {
        super(sprite);
    }
    protected GameObj(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }

    public boolean colliding(GameObj gameObj) {
        Rectangle thisHitBox = (Rectangle) hitBox.clone();
        thisHitBox.setLocation(getX() + hitBox.x, getY() + hitBox.y);

        Rectangle themHitBox = (Rectangle) gameObj.getHitBox().clone();
        themHitBox.setLocation(gameObj.getX() + themHitBox.x, gameObj.getY() + themHitBox.y);

        return thisHitBox.intersects(themHitBox);
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    public void setDestination(Point destination) {
        this.destination = destination;
    }
    public void setDestination(int x, int y) {
        destination = new Point(x, y);
    }
    public void setHitBox(int xOffset, int yOffset, int width, int height) {
        hitBox = new Rectangle(xOffset, yOffset, width, height);
    }
    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }
    public void setHitBoxVisible(boolean hitBoxVisible) {
        this.hitBoxVisible = hitBoxVisible;
    }

    public double getVelocity() {
        return velocity;
    }
    public Point getDestination() {
        return destination;
    }
    public Rectangle getHitBox() {
        return hitBox;
    }
    public boolean isHitBoxVisible() {
        return hitBoxVisible;
    }

    public void progressMovement() {
        if (destination != null) {
            if (Double.isNaN(realX) || Double.isNaN(realY)) {
                realX = getX();
                realY = getY();
            }

            double a = realX - destination.x;
            double o = realY - destination.y;
            double h = Math.sqrt((a*a) + (o*o));

            realX -= velocity*(a/h);
            realY -= velocity*(o/h);

            setLocation((int) realX, (int) realY);

            if (destination.distance(getLocation()) < velocity) {
                setLocation(destination);
                destination = null;
                realX = Double.NaN;
                realY = Double.NaN;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        double scale = GameWindow.scale;
        if (hitBoxVisible) {
            g.setColor(Color.RED);
            g.drawRect((int) ((hitBox.x + getX()) * scale),
                    (int) ((hitBox.y + getY()) * scale),
                    (int) (hitBox.width * scale),
                    (int) (hitBox.height * scale));
        }
    }
}
