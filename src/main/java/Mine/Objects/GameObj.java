package Mine.Objects;

import Mine.Window;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObj extends Obj {
    protected Rectangle hitBox = new Rectangle();
    protected boolean hitBoxVisible = false;

    protected GameObj() {
        super();
    }
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

    public void setHitBox(int xOffset, int yOffset, int width, int height) {
        hitBox = new Rectangle(xOffset, yOffset, width, height);
    }
    public void setHitBox(Rectangle collider) {
        this.hitBox = hitBox;
    }
    public void setHitBoxVisible(boolean hitBoxVisible) {
        this.hitBoxVisible = hitBoxVisible;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
    public boolean isHitBoxVisible() {
        return hitBoxVisible;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        double scale = Window.scale;
        if (hitBoxVisible) {
            g.setColor(Color.RED);
            g.drawRect((int) ((hitBox.x + getX()) * scale),
                    (int) ((hitBox.y + getY()) * scale),
                    (int) (hitBox.width * scale),
                    (int) (hitBox.height * scale));
        }
    }
}
