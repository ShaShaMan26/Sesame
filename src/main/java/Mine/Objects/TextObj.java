package Mine.Objects;

import Mine.Window;

import javax.swing.*;
import java.awt.*;

public abstract class TextObj extends Obj {
    protected JTextField textField = new JTextField();

    protected TextObj() {
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        textField.setOpaque(false);
    }
    protected TextObj(int x, int y) {
        this();
        setLocation(x, y);
    }
    protected TextObj(String text) {
        this();
        setText(text);
    }
    protected TextObj(int x, int y, String text) {
        this(x, y);
        setText(text);
    }

    public void setText(String text) {
        textField.setText(text);
        updateSize();
    }
    public void setColor(Color color) {
        textField.setForeground(color);
    }
    public void setFont(Font font) {
        textField.setFont(font);
    }

    public String getText() {
        return textField.getText();
    }
    public Color getColor() {
        return textField.getForeground();
    }
    public Font getFont() {
        return textField.getFont();
    }

    private void updateSize() {
        collider.setSize(textField.getPreferredSize());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        double scale = Window.scale;
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
