package Sesame.Objects;

import Sesame.GameWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The <code>TextObj</code> class is used to display text on screen.
 * The color, font, and size of the text displayed can be altered.
 */
public abstract class TextObj extends Obj {
    protected JTextField textField = new JTextField();

    /**
     * Default constructor.
     */
    protected TextObj() {
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField.setOpaque(false);
    }
    /**
     * Constructs an empty new <code>TextObj</code> at a location of <code>(x,y)</code>.
     * @param x horizontal position
     * @param y vertical position
     */
    protected TextObj(int x, int y) {
        this();
        setLocation(x, y);
    }
    /**
     * Constructs a new <code>TextObj</code> with a location of <code>(0,0)</code>, displaying <code>text</code>.
     * @param text textual content of <code>TextObj</code>
     */
    protected TextObj(String text) {
        this();
        setText(text);
    }
    /**
     * Constructs a new <code>TextObj</code> at location <code>(x,y)</code>, displaying <code>text</code>.
     * @param x horizontal position
     * @param y vertical position
     * @param text textual content of <code>TextObj</code>
     */
    protected TextObj(int x, int y, String text) {
        this(x, y);
        setText(text);
    }

    /**
     * Sets the content of <code>TextObj</code> to <code>text</code>.
     * @param text new text content
     */
    public void setText(String text) {
        textField.setText(text);
        updateSize();
    }
    /**
     * Sets text color to <code>color</code>.
     * @param color new text color
     */
    public void setColor(Color color) {
        textField.setForeground(color);
    }
    public void setFont(Font font) {
        textField.setFont(font);
    }

    /**
     * Accesses the current text contents of <code>TextObj</code>.
     * @return current text contents
     */
    public String getText() {
        return textField.getText();
    }
    /**
     * Accesses the current text color of <code>TextObj</code>.
     * @return current text color
     */
    public Color getColor() {
        return textField.getForeground();
    }
    /**
     * Accesses the current text font of <code>TextObj</code>.
     * @return current text font
     */
    public Font getFont() {
        return textField.getFont();
    }

    /**
     * Used when resizing underlying <code>Component</code>.
     */
    private void updateSize() {
        collider.setSize(textField.getPreferredSize());
        this.setSize(textField.getPreferredSize());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        double scale = GameWindow.scale;
        g.setColor(getColor());
        Font scaledFont = getFont();
        scaledFont = scaledFont.deriveFont((float) (getFont().getSize() * scale));
        g.setFont(scaledFont);
        int yOffset = getFontMetrics(getFont()).getAscent();
        g.drawString(getText(),
                (int) (getX() * scale),
                (int) ((getY() + yOffset) * scale));
    }
}
