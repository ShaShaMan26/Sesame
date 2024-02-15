package Mine;

import Mine.Objects.Obj;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public static double scale = 1;
    public static Canvas canvas = new Canvas();
    public static InputManager inputManager = new InputManager();
    private final JPanel framePanel = new JPanel();

    public Window() {
        super();

        framePanel.setPreferredSize(canvas.getSize());
        this.add(framePanel);
        this.pack();
        framePanel.setVisible(false);

        this.addKeyListener(inputManager);
        this.add(canvas);

        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public Window(String title) {
        this();
        this.setTitle(title);
    }

    public void fullScreen() {
        this.dispose();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.remove(framePanel);
        framePanel.setVisible(true);
        framePanel.setPreferredSize(this.getSize());
        this.add(framePanel);
        this.pack();
        framePanel.setVisible(false);

        int width = this.getWidth();
        int height = this.getHeight();

        int xOffset = (width-canvas.getWidth()) / 2;
        int yOffset = (height-canvas.getHeight()) / 2;
        canvas.setLocation(xOffset, yOffset);
    }
    public void unFullScreen() {
        this.dispose();
        this.setExtendedState(NORMAL);
        this.setUndecorated(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        canvas.setLocation(0, 0);

        updateScale();
    }
    public boolean isFullScreened() {
        return this.isUndecorated();
    }

    public void setScale(double scale) {
        if (scale < 0) {
            Window.scale = 0;
        } else {
            Window.scale = scale;
        }
    }
    public void updateScale() {
        boolean wantsToFullScreen = false;
        if (isFullScreened()) {
            unFullScreen();
            wantsToFullScreen = true;
        }

        Dimension updatedDim = new Dimension((int) (Canvas.defDim.width * scale),
                (int) (Canvas.defDim.height * scale));
        canvas.setSize(updatedDim);

        this.remove(framePanel);
        framePanel.setVisible(true);
        framePanel.setPreferredSize(canvas.getSize());
        this.add(framePanel);
        this.pack();
        framePanel.setVisible(false);

        this.setLocationRelativeTo(null);

        if (wantsToFullScreen) {
            fullScreen();
        }
    }

    public void update() {
        this.repaint();
        canvas.update();
    }
}
