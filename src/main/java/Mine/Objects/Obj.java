package Mine.Objects;

import Mine.Window;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Obj extends Component {
    protected BufferedImage sprite;
    protected Rectangle collider = new Rectangle();
    protected boolean colliderVisible = false;
    protected float opacity = 1;

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

    public boolean overlapping(Obj obj) {
        Rectangle thisCollider = (Rectangle) collider.clone();
        thisCollider.setLocation(getX() + collider.x, getY() + collider.y);

        Rectangle themCollider = (Rectangle) obj.getCollider().clone();
        themCollider.setLocation(obj.getX() + themCollider.x, obj.getY() + themCollider.y);

        return thisCollider.intersects(themCollider);
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
        collider.setSize(sprite.getWidth(), sprite.getHeight());
    }
    public void setOpacity(float opacity) {
        if (opacity > 1) {
            this.opacity = 1;
        } else if (opacity < 0) {
            this.opacity = 0;
        } else {
            this.opacity = opacity;
        }
    }
    public void setCollider(int xOffset, int yOffset, int width, int height) {
        collider = new Rectangle(xOffset, yOffset, width, height);
    }
    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }
    public void setColliderVisible(boolean colliderVisible) {
        this.colliderVisible = colliderVisible;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
    public float getOpacity() {
        return opacity;
    }
    public Rectangle getCollider() {
        return collider;
    }
    public boolean isColliderVisible() {
        return colliderVisible;
    }

    @Override
    public void paint(Graphics g) {
        double scale = Window.scale;

        ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g.drawImage(sprite,
                (int) (getX() * scale),
                (int) (getY() * scale),
                (int) (sprite.getWidth() * scale),
                (int) (sprite.getHeight() * scale),
                null);
        ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        if (colliderVisible) {
            g.setColor(Color.GREEN);
            g.drawRect((int) ((collider.x + getX()) * scale),
                    (int) ((collider.y + getY()) * scale),
                    (int) (collider.width * scale),
                    (int) (collider.height * scale));
        }
    }

    public abstract void update();
}
