package Mine;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private int scale = 1;
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
        Dimension updatedDim = new Dimension(Canvas.defDim.width*scale, Canvas.defDim.height*scale);
        canvas.setPreferredSize(updatedDim);

        this.pack();
        this.setLocationRelativeTo(null);

        // set scale of all objs
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getScale() {
        return scale;
    }
}
