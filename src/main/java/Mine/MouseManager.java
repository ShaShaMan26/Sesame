package Mine;

import Mine.Objects.Obj;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The <code>MouseManager</code> class manages access to information regarding the user's mouse.
 * Indented to act as a static helper class for <code>GameWindow</code>.
 */
public class MouseManager implements MouseListener {
    private boolean mousePressed = false;

    /**
     * Accesses the location of the user's mouse.
     * @return <code>Point</code> containing the current x and y position
     * of the user's mouse relative to the current <code>Canvas</code>.
     * <p>
     *     <code>null</code> if outside of <code>Canvas</code>
     * </p>
     */
    public Point getMousePos() {
        Point mousePos = GameWindow.canvas.getMousePosition();
        double scale = GameWindow.scale;
        if (mousePos != null) {
            int x = (int) (mousePos.getX() / scale);
            int y = (int) (mousePos.getY() / scale);
            mousePos.setLocation(x, y);
        }
        return mousePos;
    }

    /**
     * Indicates whether the user's mouse is currently within an <code>Obj</code>
     * element's <code>collider</code>.
     * @param obj target <code>Obj</code> whose <code>collider</code> will be checked against
     * @return <code>true</code> if mouse is within <code>obj</code>'s <code>collider</code>;
     * <code>false</code> if not
     */
    public boolean isMouseOver(Obj obj) {
        Point mousePos = getMousePos();

        if (mousePos != null) {
            return obj.contains(mousePos);
        }
        return false;
    }

    /**
     * Indicates if any of the user's mouse buttons are pressed at the current moment.
     * @return <code>true</code> if any button is pressed; <code>false</code> if none are
     */
    public boolean isMousePressed() {
        return mousePressed;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }
}
