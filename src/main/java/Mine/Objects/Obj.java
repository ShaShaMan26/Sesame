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

    private Rectangle getOffsetCollider() {
        Rectangle offsetCollider = (Rectangle) collider.clone();
        offsetCollider.setLocation(getX() + collider.x, getY() + collider.y);
        return offsetCollider;
    }
    public boolean overlapping(Obj obj) {
        Rectangle thisCollider = getOffsetCollider();
        Rectangle themCollider = obj.getOffsetCollider();
        return thisCollider.intersects(themCollider);
    }
    public static boolean overlapping(Obj obj1, Obj ob2) {
        return obj1.overlapping(ob2);
    }
    public boolean contains(Point point) {
        return getOffsetCollider().contains(point);
    }
    public boolean clicked() {
        return Window.mouseManager.isMousePressed() && Window.mouseManager.isMouseOver(this);
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        collider.setSize(getSize());
    }
    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        collider.setSize(getSize());
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
        this.setSize(sprite.getWidth(), sprite.getHeight());
        collider.setSize(getSize());
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

        if (sprite != null) {
            ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g.drawImage(sprite,
                    (int) (getX() * scale),
                    (int) (getY() * scale),
                    (int) (getWidth() * scale),
                    (int) (getHeight() * scale),
                    null);
            ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }

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
