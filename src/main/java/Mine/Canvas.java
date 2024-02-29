package Mine;

import Mine.Objects.Obj;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <code>Canvas</code> is an extension of the <code>JPanel</code> class.
 * It is essentially a helper class for <code>GameWindow</code> and so
 * isn't meant to ever be used on its own.
 * <p>
 *     This class is where <code>Obj</code> elements are stored, updated, and rendered.
 * </p>
 */
public class Canvas extends JPanel {
    private Color bgColor = Color.WHITE;
    private BufferedImage bgImg = null;
    public static Dimension defDim = new Dimension(640, 480);
    private final ArrayList<Obj>[] layers = new ArrayList[5];

    /**
     * Constructs a new <code>Canvas</code> at a scale of <code>1</code>.
     */
    public Canvas() {
        this.setLayout(null);
        this.setSize(defDim);

        for (int i = 0; i < layers.length; i++) {
            layers[i] = new ArrayList<>();
        }
    }

    /**
     * Sets the background color of the current <code>Canvas</code>.
     * @param bgColor <code>Color</code> value to set background to.
     *                <code>WHITE</code> by default
     */
    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    /**
     * Sets the background image of the current <code>Canvas</code>.
     * @param bgImg <code>BufferedImage</code> to be displayed as background.
     *              Set to <code>null</code> for no background; default
     */
    public void setBgImg(BufferedImage bgImg) {
        this.bgImg = bgImg;
    }

    /**
     * Returns the current <code>Color</code> of the background.
     * @return background <code>Color</code>; null if none
     */
    public Color getBgColor() {
        return bgColor;
    }

    /**
     * Returns the current <code>BufferedImage</code> of the background.
     * @return background image; null if none
     */
    public BufferedImage getBgImg() {
        return bgImg;
    }

    /**
     * Sets a designated <code>Obj</code>'s render layer.
     * @param obj element to be reordered
     * @param newLayer new render layer value.
     *                 <p>
     *                     <STRONG>WARNING:</STRONG> must be between
     *                     <code>0</code> and <code>4</code> (inclusive)
     *                 </p>
     */
    public void changeObjLayer(Obj obj, int newLayer) {
        if (newLayer > 4 || newLayer < 0) {
            throw new IllegalArgumentException("Value of layer must be between 0 and 4 (inclusive).");
        }

        for (ArrayList<Obj> layer : layers) {
            if (layer.contains(obj)) {
                layer.remove(obj);
                this.add(obj, newLayer);
            }
        }
    }

    /**
     * Adds an <code>Obj</code> element at render layer <code>0</code> to current <code>Canvas</code>.
     * @param obj element to be added
     */
    public void add(Obj obj) {
        layers[0].add(obj);
    }

    /**
     * Adds an <code>Obj</code> element to current <code>Canvas</code> at the designated render layer.
     * @param obj element to be added
     * @param layer new render layer value.
     *                 <p>
     *                     <STRONG>WARNING:</STRONG> must be between
     *                     <code>0</code> and <code>4</code> (inclusive)
     *                 </p>
     */
    public void add(Obj obj, int layer) {
        if (layer > 4 || layer < 0) {
            throw new IllegalArgumentException("Value of layer must be between 0 and 4 (inclusive).");
        }
        layers[layer].add(obj);
    }

    /**
     * Empties the current <code>Canvas</code> of all <code>Obj</code> elements.
     */
    public void clear() {
        for (ArrayList<Obj> layer : layers) {
            layer.clear();
        }
    }

    /**
     * Calls the <code>update()</code> method on all elements of the current <code>Canvas</code>.
     */
    public void update() {
        for (ArrayList<Obj> layer : layers) {
            for (Obj obj : layer) {
                obj.update();
            }
        }
    }

    /**
     * Draws the background color, image, and all elements of the current <code>Canvas</code>.
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(bgColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        // make scale <3
        g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), null);

        for (ArrayList<Obj> layer : layers) {
            for (Obj obj : layer) {
                obj.paint(g);
            }
        }
    }
}
