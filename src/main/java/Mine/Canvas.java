package Mine;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    private Color bgColor = Color.WHITE;
    public static Dimension defDim = new Dimension(640, 480);

    public Canvas() {
        this.setPreferredSize(defDim);
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(bgColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
