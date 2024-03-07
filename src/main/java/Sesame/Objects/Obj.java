package Sesame.Objects;

import Sesame.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <code>Obj</code> is an abstract class intended to be used in making game objects.
 * It extends the <code>Component</code> class and so has access to its locational and sizing abilities,
 * but also has a <code>collider</code> used for collision. The class also has a <code>sprite</code>
 * that is rendered upon call of the <code>paint</code> method and can be drawn at varying opacities.
 */
public abstract class Obj extends Component {
    protected BufferedImage sprite;
    protected Rectangle collider = new Rectangle();
    protected boolean colliderVisible = false;
    protected float opacity = 1;

    /**
     * Default constructor.
     */
    protected Obj() {
    }
    /**
     * Constructs a new <code>Obj</code> at a location of <code>(x,y)</code>
     * with a <code>null</code> <code>sprite</code>
     * and a <code>collider</code> with all values at <code>0</code>.
     * @param x horizontal position
     * @param y vertical position
     */
    protected Obj(int x, int y) {
        setLocation(x, y);
    }
    /**
     * Constructs a new <code>Obj</code> with a sprite of <code>sprite</code>
     * at a location of <code>(0,0)</code>
     * and a <code>collider</code> sized to <code>sprite</code> with offsets of <code>0</code>.
     * @param sprite <code>BufferedImage</code> to be set as <code>Obj</code>'s <code>sprite</code>
     */
    protected Obj(BufferedImage sprite) {
        setSprite(sprite);
    }
    /**
     * Constructs a new <code>Obj</code> at a location of <code>(x,y)</code>
     * with a sprite of <code>sprite</code>
     * and a <code>collider</code> sized to <code>sprite</code> with offsets of <code>0</code>.
     * @param x horizontal position
     * @param y vertical position
     * @param sprite <code>BufferedImage</code> to be set as <code>Obj</code>'s <code>sprite</code>
     */
    protected Obj(int x, int y, BufferedImage sprite) {
        this(x, y);
        setSprite(sprite);
    }

    /**
     * Accesses the <code>Obj</code>'s <code>collider</code>, adjusted for offset values.
     * @return adjusted <code>collider</code>
     */
    private Rectangle getOffsetCollider() {
        Rectangle offsetCollider = (Rectangle) collider.clone();
        offsetCollider.setLocation(getX() + collider.x, getY() + collider.y);
        return offsetCollider;
    }
    /**
     * Checks if a designated <code>Obj</code>'s <code>collider</code> is overlapping
     * this <code>Obj</code>'s <code>collider</code>.
     * @param obj <code>Obj</code> to check against
     * @return <code>true</code> if <code>colliders</code> are overlapping;
     * <code>false</code> if not
     */
    public boolean overlapping(Obj obj) {
        Rectangle thisCollider = getOffsetCollider();
        Rectangle themCollider = obj.getOffsetCollider();
        return thisCollider.intersects(themCollider);
    }
    /**
     * Checks if two <code>Obj</code>s' <code>colliders</code> are overlapping.
     * @param obj1 first <code>Obj</code> to be checked
     * @param ob2 second <code>Obj</code> to be checked
     * @return <code>true</code> if <code>colliders</code> are overlapping;
     * <code>false</code> if not
     */
    public static boolean overlapping(Obj obj1, Obj ob2) {
        return obj1.overlapping(ob2);
    }
    /**
     * Checks if a designated <code>Point</code> is within the bounds of an
     * <code>Obj</code>'s <code>collider</code>.
     * @param point location to be checked for
     * @return <code>true</code> if <code>collider</code> contains point; <code>false</code> if not.
     */
    public boolean contains(Point point) {
        return getOffsetCollider().contains(point);
    }
    /**
     * Checks if an <code>Obj</code> is currently being clicked
     * (a mouse button is currently being held down and the mouse
     * is within said <code>Obj</code>'s <code>collider</code>).
     * @return <code>true</code> if <code>Obj</code> is currently being clicked;
     * <code>false</code> if not
     */
    public boolean clicked() {
        return GameWindow.mouseManager.isMousePressed() && GameWindow.mouseManager.isMouseOver(this);
    }

    /**
     * Overrides the <code>Component</code> class method of the same name,
     * but also resizes the <code>Obj</code>'s <code>collider</code>.
     * @param d the dimension specifying the new size
     *          of this component
     */
    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        collider.setSize(getSize());
    }
    /**
     * Overrides the <code>Component</code> class method of the same name,
     * but also resizes the <code>Obj</code>'s <code>collider</code>.
     * @param width the new width of this component in pixels
     * @param height the new height of this component in pixels
     */
    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        collider.setSize(getSize());
    }

    /**
     * Sets <code>Obj</code>'s sprite to a designated <code>BufferedImage</code>.
     * <p>
     *     Sizes <code>Obj</code> (along with its <code>collider</code>) to the new <code>sprite</code>'s size.
     * </p>
     * @param sprite new <code>BufferedImage</code> to be used as the <code>Obj</code>'s <code>sprite</code>.
     */
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
        this.setSize(sprite.getWidth(), sprite.getHeight());
    }
    /**
     * Sets the render opacity of <code>Obj</code>.
     * <p>
     *     Opacity adjustment works as a multiplier, so a value of <code>.5</code>
     *     will render the <code>sprite</code> at 50% opacity.
     * </p>
     * @param opacity <code>float</code> multiplier value.
     *               <p>
     *                  Clamps to values within the range of <code>0</code> to <code>1</code>
     *               </p>
     */
    public void setOpacity(float opacity) {
        if (opacity > 1) {
            this.opacity = 1;
        } else if (opacity < 0) {
            this.opacity = 0;
        } else {
            this.opacity = opacity;
        }
    }

    /**
     * Sets <code>collider</code>.
     * @param xOffset x value <STRONG>relative</STRONG> to <code>Obj</code>'s x position
     * @param yOffset y value <STRONG>relative</STRONG> to <code>Obj</code>'s y position
     * @param width horizontal length of <code>collider</code>
     * @param height vertical length of <code>collider</code>
     */
    public void setCollider(int xOffset, int yOffset, int width, int height) {
        collider = new Rectangle(xOffset, yOffset, width, height);
    }
    /**
     * Sets <code>collider</code>.
     * @param collider <code>Rectangle</code> to be used as new <code>collider</code>.
     *                 <p>
     *                     <STRONG>WARNING:</STRONG> x and y values will be
     *                     <STRONG>relative</STRONG> to <code>Obj</code>'s location
     *                 </p>
     */
    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }
    /**
     * Makes collider to visible or invisible with respect to the value of <code>colliderVisible</code>.
     * @param colliderVisible <code>boolean</code> value: <code>true</code> for visible;
     *                        <code>false</code> for invisible
     */
    public void setColliderVisible(boolean colliderVisible) {
        this.colliderVisible = colliderVisible;
    }

    /**
     * Accesses the <code>Obj</code>'s current sprite.
     * @return <code>Obj</code>'s current sprite as a <code>BufferedImage</code>
     */
    public BufferedImage getSprite() {
        return sprite;
    }
    /**
     * Accesses the current <code>float</code> value of <code>Obj</code>'s render opacity.
     * @return render opacity as a <code>float</code>
     */
    public float getOpacity() {
        return opacity;
    }
    /**
     * Accesses <code>collider</code>.
     * @return <code>Rectangle</code> representing current <code>collider</code>
     */
    public Rectangle getCollider() {
        return collider;
    }
    /**
     * Indicates the current visibility of <code>collider</code>.
     * @return <code>true</code> if visible; <code>false</code> if not
     */
    public boolean isColliderVisible() {
        return colliderVisible;
    }

    @Override
    public void paint(Graphics g) {
        double scale = GameWindow.scale;

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

    /**
     * Abstract method that must be implemented. Will be called each frame and so
     * should be implementation of <code>Obj</code>'s update loop.
     */
    public abstract void update();
}
