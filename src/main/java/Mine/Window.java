package Mine;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public static double scale = 1;
    public static Canvas canvas = new Canvas();
    public static InputManager inputManager = new InputManager();

    public Window() {
        super();

        this.addKeyListener(inputManager);
        this.add(canvas);
        this.pack();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public Window(String title) {
        this();
        this.setTitle(title);
    }
    public Window(ImageIcon imageIcon) {
        this();
        this.setIconImage(imageIcon.getImage());
    }
    public Window(String title, ImageIcon imageIcon) {
        this(title);
        this.setIconImage(imageIcon.getImage());
    }

    public void updateScale() {
        Dimension updatedDim = new Dimension((int) (Canvas.defDim.width*scale), (int) (Canvas.defDim.height*scale));
        canvas.setPreferredSize(updatedDim);

        this.pack();
        canvas.setLayout(null);
        this.setLocationRelativeTo(null);
    }

    public void setScale(int scale) {
        if (scale > 0) {
            Window.scale = scale;
        } else if (scale < 0) {
            Window.scale = Math.abs((double) 1/scale);
        } else {
            // make fullscreen
        }

        updateScale();
    }
}
