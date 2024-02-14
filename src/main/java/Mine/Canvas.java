package Mine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {
    private Color bgColor = Color.WHITE;
    private BufferedImage bgImg = null;
    public static Dimension defDim = new Dimension(640, 480);

    public Canvas() {
        this.setLayout(null);
        this.setSize(defDim);
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }
    public void setBgImg(BufferedImage bgImg) {
        this.bgImg = bgImg;
    }

    public Color getBgColor() {
        return bgColor;
    }
    public BufferedImage getBgImg() {
        return bgImg;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(bgColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        // make scale <3
        g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), null);

        for (Component c : getComponents()) {
            c.paint(g);
        }
    }
}
