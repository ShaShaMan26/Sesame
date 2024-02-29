package Mine;

import javax.swing.*;
import java.awt.*;

/**
 * <code>GameWindow</code> is an extension of the <code>JFrame</code> class with
 * features made to make game development easier and faster.
 * This class contains a <code>canvas</code> used to host <code>Obj</code> class elements.
 * It also provides static access to an <code>InputManager</code>, <code>AudioManager</code>,
 * and <code>MouseManager</code>.
 */
public class GameWindow extends JFrame {
    public static double scale = 1;
    public static Canvas canvas = new Canvas();
    public static InputManager inputManager = new InputManager();
    public static AudioManager audioManager = new AudioManager();
    public static MouseManager mouseManager = new MouseManager();
    private final JPanel framePanel = new JPanel();

    /**
     * Constructs a new <code>GameWindow</code> at a scale of 1.
     */
    public GameWindow() {
        super();

        framePanel.setPreferredSize(canvas.getSize());
        this.add(framePanel);
        this.pack();
        framePanel.setVisible(false);

        this.addKeyListener(inputManager);
        this.addMouseListener(mouseManager);
        this.add(canvas);

        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Constructs a new <code>GameWindow</code> at a scale of 1 with a window title of <code>title</code>.
     * @param title Title of the window
     */
    public GameWindow(String title) {
        this();
        this.setTitle(title);
    }

    /**
     * Fills the screen with an undecorated window of black, overlaid by <code>canvas</code> at its current scale.
     * This full screens the application while maintaining the scalability of <code>canvas</code>.
     */
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

    /**
     * Returns the <code>GameWindow</code> to a decorated window the size of <code>canvas</code> at its current scale.
     */
    public void unFullScreen() {
        this.dispose();
        this.setExtendedState(NORMAL);
        this.setUndecorated(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        canvas.setLocation(0, 0);

        updateScale();
    }

    /**
     * Indicates whether <code>GameWindow</code> is currently fullscreen. <code>GameWindows</code>
     * are by default not fullscreen.
     * @return <code>true</code> if <code>GameWindow</code> is fullscreen; <code>false</code> if not
     */
    public boolean isFullScreened() {
        return this.isUndecorated();
    }

    /**
     * Sets the rendering scale of <code>canvas</code>. The value of <code>scale</code> is used as a multiplier
     * so a scale of x1.5 would require a <code>scale</code> value of <code>1.5</code>.
     * @param scale Value of which <code>canvas</code> will be scaled by. Clamps to a value of <code>0</code>
     *              if <code>scale < 0</code>
     */
    public void setScale(double scale) {
        if (scale < 0) {
            GameWindow.scale = 0;
        } else {
            GameWindow.scale = scale;
        }
    }

    /**
     * Updates <code>canvas</code> scaling with respect to the current value of <code>scale</code>.
     * Also updates fullscreen state.
     * <p>
     *     <STRONG>WARNING:</STRONG>
     *     Must be called in order to apply augmentations to <code>canvas</code> scaling and/or
     *     <code>GameWindow</code> fullscreen.
     * </p>
     */
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

    /**
     * Renders the current frame and updates all <code>canvas</code> elements.
     */
    public void update() {
        this.repaint();
        canvas.update();
    }
}
