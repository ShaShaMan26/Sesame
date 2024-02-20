package Mine;

import Mine.Objects.Obj;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener {
    private boolean mousePressed = false;

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

    public boolean isMouseOver(Obj obj) {
        Point mousePos = getMousePos();

        if (mousePos != null) {
            return obj.contains(mousePos);
        }
        return false;
    }

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
