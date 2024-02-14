package Mine.Objects;

import Mine.Window;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Obj extends Component {
    protected BufferedImage sprite;
    protected Rectangle hitBox = new Rectangle(0, 0, 0, 0);
    protected boolean hitBoxVisible = false;

    protected Obj() {
    }
    protected Obj(int x, int y) {
        this();
        setLocation(x, y);
    }
    protected Obj(BufferedImage sprite) {
        this();
        setSprite(sprite);
    }
    protected Obj(int x, int y, BufferedImage sprite) {
        this(x, y);
        setSprite(sprite);
    }

    public boolean collidingWith(Obj obj) {
        Rectangle thisHitBox = (Rectangle) hitBox.clone();
        thisHitBox.setLocation(getX() + hitBox.x, getY() + hitBox.y);

        Rectangle themHitBox = (Rectangle) obj.getHitBox().clone();
        themHitBox.setLocation(obj.getX() + themHitBox.x, obj.getY() + themHitBox.y);

        return thisHitBox.intersects(themHitBox);
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
        hitBox.setSize(sprite.getWidth(), sprite.getHeight());
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

    public BufferedImage getSprite() {
        return sprite;
    }
    public Rectangle getHitBox() {
        return hitBox;
    }
    public boolean isHitBoxVisible() {
        return hitBoxVisible;
    }

    @Override
    public void paint(Graphics g) {
        double scale = Window.scale;

        g.drawImage(sprite,
                (int) (getX() * scale),
                (int) (getY() * scale),
                (int) (sprite.getWidth() * scale),
                (int) (sprite.getHeight() * scale),
                null);

        if (hitBoxVisible) {
            g.setColor(Color.GREEN);
            g.drawRect((int) ((hitBox.x + getX()) * scale),
                    (int) ((hitBox.y + getY()) * scale),
                    (int) (hitBox.width * scale),
                    (int) (hitBox.height * scale));
        }
    }
}
